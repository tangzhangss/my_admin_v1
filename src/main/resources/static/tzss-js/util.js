/**
 *此JS文件用于关系系统多个页面 不可删除
 * 2020年新的页面不再使用layui框架 换 elements
 */


/**
 * 开启图片懒加载
 */
layui.use('flow', function(){
    let flow = layui.flow;
    //当你执行这样一个方法时，即对页面中的全部带有lay-src的img元素开启了懒加载（当然你也可以指定相关img）
    flow.lazyimg();
});

/**
 * 仅限web端使用
 * @type {Object}
 */
const mylayer = new Object();
/**
 * 弹出框设置
 * icon 0-6 默认
 *    -1 黑色
 *    5 红色 苦脸
 *    6 绿色 笑脸
 *    4 灰色 锁
 *    3 橙色 问号
 *    2 红色 ×
 *    1 绿色 ✔
 **/
mylayer.msg = function (text,icon = -1) {
    layui.use('layer', function(){
        let l = layui.layer;
        l.msg(text,{icon:icon});
    });
}
/**
 * 提示框
 */
mylayer.alert = function (text) {
    layui.use('layer', function(){
        let l = layui.layer;
        l.open({
            type: 0,
            content: text //这里content是一个普通的String
        });
    });
}

/**
 * 询问框
 */
mylayer.confirm = function (text,func) {
    layui.use('layer', function(){
        let l = layui.layer;
        l.confirm(text, {icon: 3, title:"提示"}, function(index){
            func();
            layer.close(index);
        });
    });

}

/**
 * 加载层
 * @param text
 * @param icon 0 红色 1 默认
 * @param shadeClose 是否点击遮罩关闭
 */
mylayer.load = function (icon = 0,shadeClose=true) {
    layui.use('layer', function(){
        let l = layui.layer;
        l.open({
            shadeClose:shadeClose,
            type:3,
            offset:['48vh', '48vw'],//必须设置，不然不会水平居中 这东西有默认宽度 然而左对齐
            icon:icon
        });
    });
}
/**
 *静态框
 * @param jquery_attr  静态框的对应jquery对象
 */
mylayer.openModal = function(jquery_attr,title,area){
    title = undefined==title?['静态框','font-size:18px;']:title;
    area = undefined==area?['1000px', '600px']:area;
    layui.use('layer', function() {
        let l = layui.layer;
        l.open({
            type: 1,
            content: jquery_attr,
            shadeClose:true,
            area:area,
            title:title
            //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
        });
    });
}
/**
 * 关闭弹出层
 */
mylayer.close = function(){
    layui.use('layer', function() {
        let l = layui.layer;
        l.close(l.index);
    });
}
/**
 *文件上传
 * elem 绑定元素  如:#demo
 * fuc 回调函数
 **/
mylayer.upload=function(elem,fuc,fucerr){
    layui.use('upload', function(){
        let upload = layui.upload;
        //执行实例
        upload.render({
            elem:elem,//绑定元素
            url: '/util/upload_picture', //上传接口
            done: function(res){
                //上传完毕回调
                fuc(res.data);
            },error: function(res){
                //请求异常回调
                fucerr(res.data);
            }
        });
    });
}
/**
 * 上传到服务器本地  cert.文件
 */
mylayer.uploadLocalCertFile=function(elem,fuc,fucerr){
    layui.use('upload', function(){
        let upload = layui.upload;
        //执行实例
        upload.render({
            elem:elem,//绑定元素
            url: '/util/upload_local_cert_file', //上传接口
            accept:"file",
            data:{"path":"/etc/cert"},//文件上传路径
            done: function(res){
                //上传完毕回调
                fuc(res.data);
            },error: function(res){
                //请求异常回调
                fucerr(res.data);
            }
        });
    });
}

