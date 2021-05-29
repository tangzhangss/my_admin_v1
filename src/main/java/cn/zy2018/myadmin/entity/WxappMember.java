package cn.zy2018.myadmin.entity;

import cn.zy2018.myadmin.utils.CalculateUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/2/11.
 * 微信引用 程序用户表
 */
public class WxappMember implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;//用户ID
    private String openid;//小程序openid
    private String wxopenid;//公众号openid
    private Integer gender;//性别 1 男 2女
    private String sex;//性别 1 男 2女
    private String avatars;//微信头像
    private String nickname;//
    private String nicknameDecodeBase64;//base64解码之后的
    private String nicknameEncodeBase64;//编码之后
    private String province;//所在省
    private String city;//所在市
    private int online;


    private Integer oid;//项目id
    private String unionid;//开放平台唯一

    private Integer identity;//身份  【1普通默认 2医生  全v健康】

    private String createTime;//注册时间
    private String realname;//真实姓名
    private String realavatars;//真实头像
    private String contact;//联系方式
    private String birthday;//出生年月

    private int isAttention=0;//是否关注公众号

    public int getIsAttention() {
        return isAttention;
    }

    public void setIsAttention(int isAttention) {
        this.isAttention = isAttention;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getWxopenid() {
        return wxopenid;
    }

    public void setWxopenid(String wxopenid) {
        this.wxopenid = wxopenid;
    }

    public String getSex() {
        if(gender!=null){
            return gender==1?"男":"女";
        }
        return null;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAvatars() {
        return avatars;
    }

    public void setAvatars(String avarars) {
        this.avatars = avarars;
    }

    public String getNickname() {
        return nickname;
    }
    //解码之后的
    public String getNicknameDecodeBase64() {
        //base64转码
        if(StringUtils.isNotBlank(nickname)){
            return new String(Base64.decodeBase64(nickname));
        }else{
            return nickname;
        }
    }
    public String getNicknameEncodeBase64() {
        //base64转码
        if(StringUtils.isNotBlank(nickname)){
            return new String(Base64.encodeBase64(nickname.getBytes()));
        }else{
            return nickname;
        }
    }
    public void setNickname(String nickname) {
        //base64编码过的
        this.nickname = nickname;
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

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        //去掉timestamp后面的0
        this.createTime = createTime.substring(0,createTime.lastIndexOf(":"));
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getRealavatars() {
        return realavatars;
    }

    public void setRealavatars(String realavatars) {
        this.realavatars = realavatars;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    //年龄 ——————根据出生日期计算
    public Integer getAge() {
        if(StringUtils.isNotBlank(birthday)){
            return CalculateUtil.getAge(birthday);
        }
        return null;
    }

    @Override
    public String toString() {
        return "WxappMember{" +
                "id=" + id +
                ", openid='" + openid + '\'' +
                ", wxopenid='" + wxopenid + '\'' +
                ", gender=" + gender +
                ", sex='" + sex + '\'' +
                ", avatars='" + avatars + '\'' +
                ", nickname='" + nickname + '\'' +
                ", nicknameDecodeBase64='" + nicknameDecodeBase64 + '\'' +
                ", nicknameEncodeBase64='" + nicknameEncodeBase64 + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", oid=" + oid +
                ", unionid='" + unionid + '\'' +
                ", identity=" + identity +
                ", createTime='" + createTime + '\'' +
                ", realname='" + realname + '\'' +
                ", realavatars='" + realavatars + '\'' +
                ", contact='" + contact + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
