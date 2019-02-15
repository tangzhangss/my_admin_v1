package zyrs.xyz.obadmin.bean;

import org.apache.commons.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;
import zyrs.xyz.obadmin.utils.CalculateUtil;

import javax.xml.crypto.Data;

/**
 * Created by Administrator on 2019/2/11.
 * 小程序用户表
 */
public class WxappMember {
    private Integer id;//用户ID
    private String openid;//小程序openid
    private String wxopenid;//公众号openid
    private Integer sex;//性别 1 男 2女
    private String avatars;//微信头像
    private String nickname;//base64编码之后的
    private String province;//所在省
    private String city;//所在市

    private Integer identity;//身份  【1普通默认 2患者 3 医生  全v健康】

    private String createTime;//注册时间
    private String realname;//真实姓名
    private String realavatars;//真实头像
    private String contact;//联系方式
    private String birthday;//出生年月

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~医生专属字段~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private String education;//教育资料
    private String goodField;//擅长领域
    private String hospital;//坐诊医院
    private String level;//医生等级 v1>
    private String cardFace;//省份证正面
    private String cardBack;//身份证反面
    private String certificateDoctor;//医师资格证
    private String certificatePractice;//执业资格证
    private Integer patientNum;//病人数量

    private int isaudit;//医生状态 1待审核 2通过 3 不通过
    private String message;//信息 可能是审核失败原因


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
        return sex==1?"男":"女";
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAvatars() {
        return avatars;
    }

    public void setAvatars(String avarars) {
        this.avatars = avarars;
    }

    public String getNickname() {
        //base64转码
        if(nickname!=null){
            return new String(Base64.decodeBase64(nickname));
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
        return CalculateUtil.getAge(birthday);
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGoodField() {
        return goodField;
    }

    public void setGoodField(String goodField) {
        this.goodField = goodField;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCardFace() {
        return cardFace;
    }

    public void setCardFace(String cardFace) {
        this.cardFace = cardFace;
    }

    public String getCardBack() {
        return cardBack;
    }

    public void setCardBack(String cardBack) {
        this.cardBack = cardBack;
    }

    public String getCertificateDoctor() {
        return certificateDoctor;
    }

    public void setCertificateDoctor(String certificateDoctor) {
        this.certificateDoctor = certificateDoctor;
    }

    public String getCertificatePractice() {
        return certificatePractice;
    }

    public void setCertificatePractice(String certificate) {
        this.certificatePractice = certificate;
    }

    public Integer getIsaudit() {
        return isaudit;
    }

    public void setIsaudit(int isaudit) {
        this.isaudit = isaudit;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getPatientNum() {
        return patientNum;
    }

    public void setPatientNum(Integer patientNum) {
        this.patientNum = patientNum;
    }

    @Override
    public String toString() {
        return "WxappMember{" +
                "id=" + id +
                ", openid='" + openid + '\'' +
                ", wxopenid='" + wxopenid + '\'' +
                ", sex=" + sex +
                ", avatars='" + avatars + '\'' +
                ", nickname='" + nickname + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", identity=" + identity +
                ", createTime='" + createTime + '\'' +
                ", realname='" + realname + '\'' +
                ", realavatars='" + realavatars + '\'' +
                ", contact='" + contact + '\'' +
                ", birthday='" + birthday + '\'' +
                ", education='" + education + '\'' +
                ", goodField='" + goodField + '\'' +
                ", hospital='" + hospital + '\'' +
                ", level='" + level + '\'' +
                ", cardFace='" + cardFace + '\'' +
                ", cardBack='" + cardBack + '\'' +
                ", certificateDoctor='" + certificateDoctor + '\'' +
                ", CertificatePractice='" + certificatePractice + '\'' +
                ", patientNum=" + patientNum +
                ", isaudit=" + isaudit +
                ", message='" + message + '\'' +
                '}';
    }
}
