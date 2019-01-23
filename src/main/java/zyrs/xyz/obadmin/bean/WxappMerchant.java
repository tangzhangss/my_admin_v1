package zyrs.xyz.obadmin.bean;

public class WxappMerchant {
    private String mchid; //支付商户id
    private String mchkey;//商户密钥
    private String tradeType;//交易类型
    private String body;//商品类型
    private String sslcertPath;
    private String sslkeyPath;
    private String rootPath;
    private Integer oid;//对应项目小程序id

    @Override
    public String toString() {
        return "WxappMerchant{" +
                "mchid='" + mchid + '\'' +
                ", mchkey='" + mchkey + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", body='" + body + '\'' +
                ", sslcertPath='" + sslcertPath + '\'' +
                ", sslkeyPath='" + sslkeyPath + '\'' +
                ", rootPath='" + rootPath + '\'' +
                ", oid=" + oid +
                '}';
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

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }
}
