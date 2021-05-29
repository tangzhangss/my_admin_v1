/**
 * @file AMR 录音、转换、播放器
 * @author BenzLeung(https://github.com/BenzLeung)
 * @date 2017/11/12
 * Created by JetBrains PhpStorm.
 *
 * 每位工程师都有保持代码优雅的义务
 * each engineer has a duty to keep the code elegant
 */

import RecorderControl from "./RecorderControl";
import amrWorker from "./amrnb";

const amrWorkerStr = amrWorker.toString()
    .replace(/^\s*function.*?\(\)\s*{/, '')
    .replace(/}\s*$/, '');
const amrWorkerURLObj = (window.URL || window.webkitURL).createObjectURL(new Blob([amrWorkerStr], {type:"text/javascript"}));

export default class BenzAMRRecorder {

    _isInit = false;

    _isInitRecorder = false;

    _recorderControl = new RecorderControl();

    _samples = new Float32Array(0);

    _rawData = new Uint8Array(0);

    _blob = null;

    _onEnded = null;

    _onAutoEnded = null;

    _onPlay = null;

    _onStop = null;

    _onStartRecord = null;

    _onCancelRecord = null;

    _onFinishRecord = null;

    _isPlaying = false;
    
    constructor() {
    }

    /**
     * 是否已经初始化
     * @return {boolean}
     */
    isInit() {
        return this._isInit;
    }

    /**
     * 使用浮点数据初始化
     * @param {Float32Array} array
     * @return {Promise}
     */
    initWithArrayBuffer(array) {
        if (this._isInit || this._isInitRecorder) {
            throw new Error('AMR has been initialized. For a new AMR, please generate a new BenzAMRRecorder().');
        }
        this._playEmpty();
        return new Promise((resolve, reject) => {
            let u8Array = new Uint8Array(array);
            this.decodeAMRAsync(u8Array).then((samples) => {
                this._samples = samples;
                this._isInit = true;

                if (!this._samples) {
                    RecorderControl.decodeAudioArrayBufferByContext(array).then((data) => {
                        this._isInit = true;
                        return this.encodeAMRAsync(data, RecorderControl.getCtxSampleRate());
                    }).then((rawData) => {
                        this._rawData = rawData;
                        this._blob = BenzAMRRecorder.rawAMRData2Blob(rawData);
                        return this.decodeAMRAsync(rawData);
                    }).then((sample) => {
                        this._samples = sample;
                        resolve();
                    }).catch(() => {
                        reject(new Error('Failed to decode.'));
                    });
                } else {
                    this._rawData = u8Array;
                    resolve();
                }
            });
        });
    }

    /**
     * 使用 Blob 对象初始化（ <input type="file">）
     * @param {Blob} blob
     * @return {Promise}
     */
    initWithBlob(blob) {
        if (this._isInit || this._isInitRecorder) {
            throw new Error('AMR has been initialized. For a new AMR, please generate a new BenzAMRRecorder().');
        }
        this._playEmpty();
        this._blob = blob;
        const p = new Promise((resolve) => {
            let reader = new FileReader();
            reader.onload = function(e) {
                resolve(e.target.result);
            };
            reader.readAsArrayBuffer(blob);
        });
        return p.then((data) => {
            return this.initWithArrayBuffer(data);
        });
    }

    /**
     * 使用 url 初始化
     * @param {string} url
     * @return {Promise}
     */
    initWithUrl(url) {
        if (this._isInit || this._isInitRecorder) {
            throw new Error('AMR has been initialized. For a new AMR, please generate a new BenzAMRRecorder().');
        }
        this._playEmpty();
        const p = new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest();
            xhr.open('GET', url, true);
            xhr.responseType = 'arraybuffer';
            xhr.onload = function() {
                resolve(this.response);
            };
            xhr.onerror = function() {
                reject(new Error('Failed to fetch ' + url));
            };
            xhr.send();
        });
        return p.then((array) => {
            return this.initWithArrayBuffer(array);
        });
    }

    /**
     * 初始化录音
     * @return {Promise}
     */
    initWithRecord() {
        if (this._isInit || this._isInitRecorder) {
            throw new Error('AMR has been initialized. For a new AMR, please generate a new BenzAMRRecorder().');
        }
        this._playEmpty();
        return new Promise((resolve, reject) => {
            this._recorderControl.initRecorder().then(() => {
                this._isInitRecorder = true;
                resolve();
            }).catch((e) => {
                reject(e);
            });
        });
    }

    /**
     * init 之前先播放一个空音频。
     * 因为有些环境（如iOS）播放首个音频时禁止自动、异步播放，
     * 播放空音频防止加载后立即播放的功能失效。
     * 但即使如此，init* 仍然须放入一个用户事件中
     * @private
     */
    _playEmpty = () => {
        this._recorderControl.playPcm(new Float32Array(10), 24000);
    };

    on(action, fn) {
        if (typeof fn === 'function') {
            switch (action) {
                case 'play':
                    this._onPlay = fn;
                    break;
                case 'stop':
                    this._onStop = fn;
                    break;
                case 'ended':
                    this._onEnded = fn;
                    break;
                case 'autoEnded':
                    this._onAutoEnded = fn;
                    break;
                case 'startRecord':
                    this._onStartRecord = fn;
                    break;
                case 'cancelRecord':
                    this._onCancelRecord = fn;
                    break;
                case 'finishRecord':
                    this._onFinishRecord = fn;
                    break;
                default:
            }
        }
    }

    /**
     * 播放事件
     * @param {Function} fn
     */
    onPlay(fn) {
        this.on('play', fn);
    }

    /**
     * 停止事件（包括播放结束）
     * @param {Function} fn
     */
    onStop(fn) {
        this.on('stop', fn);
    }

    /**
     * 播放结束事件
     * @param {Function} fn
     */
    onEnded(fn) {
        this.on('ended', fn);
    }

    /**
     * 播放完毕自动结束事件
     * @param {Function} fn
     */
    onAutoEnded(fn) {
        this.on('autoEnded', fn);
    }

    /**
     * 开始录音事件
     * @param {Function} fn
     */
    onStartRecord(fn) {
        this.on('startRecord', fn);
    }

    /**
     * 结束录音事件
     * @param {Function} fn
     */
    onFinishRecord(fn) {
        this.on('finishRecord', fn);
    }

    /**
     * 放弃录音事件
     * @param {Function} fn
     */
    onCancelRecord(fn) {
        this.on('cancelRecord', fn);
    }

    _onEndCallback = () => {
        if (this._isPlaying) {
            this._isPlaying = false;
            if (this._onStop) {
                this._onStop();
            }
            if (this._onAutoEnded) {
                this._onAutoEnded();
            }
        }
        if (this._onEnded) {
            this._onEnded();
        }
    };

    /**
     * 播放
     */
    play() {
        if (!this._isInit) {
            throw new Error('Please init AMR first.');
        }
        if (this._onPlay) {
            this._onPlay();
        }
        this._isPlaying = true;
        this._recorderControl.playPcm(this._samples, this._isInitRecorder ? RecorderControl.getCtxSampleRate() : 8000, this._onEndCallback.bind(this));
    }

    /**
     * 停止
     */
    stop() {
        this._recorderControl.stopPcm();
        this._isPlaying = false;
        if (this._onStop) {
            this._onStop();
        }
    }

    /**
     * 是否正在播放
     * @return {boolean}
     */
    isPlaying() {
        return this._isPlaying;
    }

    /**
     * 开始录音
     */
    startRecord() {
        this._recorderControl.startRecord();
        if (this._onStartRecord) {
            this._onStartRecord();
        }
    }

    /**
     * 结束录音，并把录制的音频转换成 AMR
     * @return {Promise}
     */
    finishRecord() {
        return new Promise((resolve) => {
            this._recorderControl.stopRecord();
            this._recorderControl.generateRecordSamples().then((samples) => {
                this._samples = samples;
                return this.encodeAMRAsync(samples, RecorderControl.getCtxSampleRate());
            }).then((rawData) => {
                this._rawData = rawData;
                this._blob = BenzAMRRecorder.rawAMRData2Blob(this._rawData);
                this._isInit = true;
                if (this._onFinishRecord) {
                    this._onFinishRecord();
                }
                this._recorderControl.releaseRecord();
                resolve();
            });
        });
    }

    /**
     * 放弃录音
     */
    cancelRecord() {
        this._recorderControl.stopRecord();
        this._recorderControl.releaseRecord();
        if (this._onCancelRecord) {
            this._onCancelRecord();
        }
    }

    /**
     * 是否正在录音
     * @return {boolean}
     */
    isRecording() {
        return this._recorderControl.isRecording();
    }

    /**
     * 获取音频的时间长度（单位：秒）
     * @return {Number}
     */
    getDuration() {
        let rate = this._isInitRecorder ? RecorderControl.getCtxSampleRate() : 8000;
        return this._samples.length / rate;
    }

    getBlob() {
        return this._blob;
    }

    /*
    static encodeAMR(samples, sampleRate) {
        sampleRate = sampleRate || 8000;
        return AMR.encode(samples, sampleRate, 7);
    }
    */

    _runAMRWorker = (msg, resolve) => {
        const amrWorker = new Worker(amrWorkerURLObj);
        amrWorker.postMessage(msg);
        amrWorker.onmessage = (e) => {
            resolve(e.data.amr);
            amrWorker.terminate();
        };
    };

    encodeAMRAsync(samples, sampleRate) {
        return new Promise(resolve => {
            this._runAMRWorker({
                command: 'encode',
                samples: samples,
                sampleRate: sampleRate
            }, resolve);
        });
    }
    
    decodeAMRAsync(u8Array) {
        return new Promise(resolve => {
            this._runAMRWorker({
                command: 'decode',
                buffer: u8Array
            }, resolve);
        })
    }

    static rawAMRData2Blob(data) {
        return new Blob([data.buffer], {type: 'audio/amr'});
    }
}
