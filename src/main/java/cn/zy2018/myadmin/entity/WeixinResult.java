package cn.zy2018.myadmin.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Administrator on 2019/3/1.
 *
 * 微信工具返回结果集合
 *
 */
public class WeixinResult implements Serializable{
    //token
    private String access_token; //获得的凭证
    private Integer expires_in;  //凭证的有效时间，单位：秒 目前使7200秒之内的值
    private int errcode = 0; //错误码 0 请求成功 其他 同微信
    private String refresh_token;//用户刷新access_token
    private String openid;//用户openid
    private String scope;//用户授权的作用域，使用逗号（,）分隔
    private String errmsg;//错误信息
    private String msgid;//模板消息返回

    private int sex;//性别
    private String nickname;//
    private String country;//国家
    private String language;
    private String province;//所在省
    private String city;//所在市
    private String headimgurl;//微信头像 公众号
    private String[] privilege;//公众号 特权
    private String unionid;//开放平台唯一

    private String ticket;//调用微信JS接口的临时票据



    //----------
    //获取用户基本信息-可判断用户是否关注公众号
    //---------
    private int subscribe;//是否订阅公众号 0表示没有
    private String subscribe_time;//最近订阅时间
    private String remark;//运营者对用户的备注
    private String groupid;//用户所在的分组
    private ArrayList tagid_list;//用户被打上的标签ID列表
    private String subscribe_scene;//用户关注的来源
    private String qr_scene;//二维码扫码场景
    private String qr_scene_str;//场景描述

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }

    public String getSubscribe_time() {
        return subscribe_time;
    }

    public void setSubscribe_time(String subscribe_time) {
        this.subscribe_time = subscribe_time;
    }

    /**
     *用户关注主要信息
     */
    public String toStringAttention() {
        return "WeixinResult{" +
                "subscribe=" + subscribe +
                ", subscribe_time='" + subscribe_time + '\'' +
                ", remark='" + remark + '\'' +
                ", groupid='" + groupid + '\'' +
                ", tagid_list=" + tagid_list +
                ", subscribe_scene='" + subscribe_scene + '\'' +
                ", qr_scene='" + qr_scene + '\'' +
                ", qr_scene_str='" + qr_scene_str + '\'' +
                '}';
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
                ", msgid='" + msgid + '\'' +
                ", sex=" + sex +
                ", nickname='" + nickname + '\'' +
                ", country='" + country + '\'' +
                ", language='" + language + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", privilege=" + Arrays.toString(privilege) +
                ", unionid='" + unionid + '\'' +
                ", ticket='" + ticket + '\'' +
                ", subscribe=" + subscribe +
                ", subscribe_time='" + subscribe_time + '\'' +
                ", remark='" + remark + '\'' +
                ", groupid='" + groupid + '\'' +
                ", tagid_list=" + tagid_list +
                ", subscribe_scene='" + subscribe_scene + '\'' +
                ", qr_scene='" + qr_scene + '\'' +
                ", qr_scene_str='" + qr_scene_str + '\'' +
                '}';
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public ArrayList getTagid_list() {
        return tagid_list;
    }

    public void setTagid_list(ArrayList tagid_list) {
        this.tagid_list = tagid_list;
    }

    public String getSubscribe_scene() {
        return subscribe_scene;
    }

    public void setSubscribe_scene(String subscribe_scene) {
        this.subscribe_scene = subscribe_scene;
    }

    public String getQr_scene() {
        return qr_scene;
    }

    public void setQr_scene(String qr_scene) {
        this.qr_scene = qr_scene;
    }

    public String getQr_scene_str() {
        return qr_scene_str;
    }

    public void setQr_scene_str(String qr_scene_str) {
        this.qr_scene_str = qr_scene_str;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getSex() {
        return sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String[] getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String[] privilege) {
        this.privilege = privilege;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
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
