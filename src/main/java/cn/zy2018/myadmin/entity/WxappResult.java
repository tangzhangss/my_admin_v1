package cn.zy2018.myadmin.entity;

import java.util.List;
import java.util.Map;

public class WxappResult {

    //token
    private String access_token; //获得的凭证
    private Integer expires_in;  //凭证的有效时间，单位：秒 目前使7200秒之内的值
    private int errcode = 0; //错误码 0 请求成功 其他 同微信
    private String errmsg;//错误信息
    //--------------------------------------------------------
    //获取用户访问小程序数据概况
    private String ref_date;//时间
    private List<Map<String,Integer>> visit_uv_new;//新增用户留存
    private List<Map<String,Integer>> visit_uv;//活跃用户留存

    //获取用户访问小程序数据概况
    private List<Map<String,String>> list;


    //login  jscode2session返回参数
    private  String unionid;//用户在开放平台的唯一标识符
    private String  session_key;//会话密钥
    private String openid;//小程序唯一标识

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getRef_date() {
        return ref_date;
    }

    public void setRef_date(String ref_date) {
        this.ref_date = ref_date;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public List<Map<String, Integer>> getVisit_uv_new() {
        return visit_uv_new;
    }

    public void setVisit_uv_new(List<Map<String, Integer>> visit_uv_new) {
        this.visit_uv_new = visit_uv_new;
    }

    public List<Map<String, Integer>> getVisit_uv() {
        return visit_uv;
    }

    public void setVisit_uv(List<Map<String, Integer>> visit_uv) {
        this.visit_uv = visit_uv;
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

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<Map<String,String>> getList() {
        return list;
    }

    public void setList(List<Map<String,String>> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "WxappResult{" +
                "access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                ", errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                ", ref_date='" + ref_date + '\'' +
                ", visit_uv_new=" + visit_uv_new +
                ", visit_uv=" + visit_uv +
                ", list=" + list +
                ", unionid='" + unionid + '\'' +
                ", session_key='" + session_key + '\'' +
                ", openid='" + openid + '\'' +
                '}';
    }
}
