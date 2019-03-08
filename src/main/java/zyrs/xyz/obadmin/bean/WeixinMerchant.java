package zyrs.xyz.obadmin.bean;

/**
 * Created by Administrator on 2019/3/5.
 * 微信商户平台
 */
public class WeixinMerchant {
    private String mchid;
    private String mchkey;
    private String tradeType;//交易类型 目前暂定不可更改
    private String body;//商品类型
    private String sslcertPath;
    private String sslkeyPath;
    private String rootPath;
    private String oid;

    @Override
    public String toString() {
        return "WeixinMerchant{" +
                "mchid='" + mchid + '\'' +
                ", mchkey='" + mchkey + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", body='" + body + '\'' +
                ", sslcertPath='" + sslcertPath + '\'' +
                ", sslkeyPath='" + sslkeyPath + '\'' +
                ", rootPath='" + rootPath + '\'' +
                ", oid='" + oid + '\'' +
                '}';
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getMchkey() {
        return mchkey;
    }

    public void setMchkey(String mchkey) {
        this.mchkey = mchkey;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSslcertPath() {
        return sslcertPath;
    }

    public void setSslcertPath(String sslcertPath) {
        this.sslcertPath = sslcertPath;
    }

    public String getSslkeyPath() {
        return sslkeyPath;
    }

    public void setSslkeyPath(String sslkeyPath) {
        this.sslkeyPath = sslkeyPath;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }
}
