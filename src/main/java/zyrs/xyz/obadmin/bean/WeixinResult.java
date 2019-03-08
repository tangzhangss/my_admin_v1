package zyrs.xyz.obadmin.bean;

/**
 * Created by Administrator on 2019/3/1.
 *
 * 微信工具返回结果集合
 *
 */
public class WeixinResult {
    //token
    private String access_token; //获得的凭证
    private Integer expires_in;  //凭证的有效时间，单位：秒 目前使7200秒之内的值
    private int errcode = 0; //错误码 0 请求成功 其他 同微信
    private String refresh_token;//用户刷新access_token
    private String openid;//用户openid
    private String scope;//用户授权的作用域，使用逗号（,）分隔
    private String errmsg;//错误信息
    private String msgid;//模板消息返回

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    @Override
    public String toString() {
        return "WeixinResult{" +
                "access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                ", errcode=" + errcode +
                ", refresh_token='" + refresh_token + '\'' +
                ", openid='" + openid + '\'' +
                ", scope='" + scope + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", megid='" + msgid + '\'' +
                '}';
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
