package cn.zy2018.myadmin.entity;

import java.io.Serializable;

/**
 * 微信网页开发 接入sdk 配置类
 */
public class WeixinSdkConfig implements Serializable {
    private String appId;
    private String timestamp;
    private String nonceStr;
    private String signature;

    /*
     *  20200227不需要了 集群需要用redis
     */
    //    public static int jsapiTicketExpiresIn = 0;//ticket 有效时间
//    public static void setJsapiTicketExpiresIn(int jsapiTicketExpiresIn) {
//        WeixinSdkConfig.jsapiTicketExpiresIn = jsapiTicketExpiresIn;
//        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//        executorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                WeixinSdkConfig.jsapiTicketExpiresIn--;
//                if (WeixinSdkConfig.jsapiTicketExpiresIn <= 0) {
//                    executorService.shutdown();//关闭 定时器
//                }
//            }
//        }, 0, 1, TimeUnit.SECONDS);//初始化延时 时间间隙 及时单位
//    }

    public static String ticket;


    public WeixinSdkConfig() {
    }

    public WeixinSdkConfig(String appId, String timestamp, String nonceStr, String signature) {
        this.appId = appId;
        this.timestamp = timestamp;
        this.nonceStr = nonceStr;
        this.signature = signature;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

}
