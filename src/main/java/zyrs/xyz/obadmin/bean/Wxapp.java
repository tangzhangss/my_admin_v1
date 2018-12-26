package zyrs.xyz.obadmin.bean;

/**
 * 微信小程序 基本信息
 */
public class Wxapp {

    private Integer id;
    private String appid;
    private String secret;

    private Integer obId; //项目id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Integer getObId() {
        return obId;
    }

    public void setObId(Integer obId) {
        this.obId = obId;
    }

    @Override
    public String toString() {
        return "Wxapp{" +
                "id=" + id +
                ", appid='" + appid + '\'' +
                ", secret='" + secret + '\'' +
                ", obId=" + obId +
                '}';
    }
}
