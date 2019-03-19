package zyrs.xyz.obadmin.bean;

import org.apache.commons.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;
import zyrs.xyz.obadmin.utils.CalculateUtil;

import java.util.List;

/**
 * Created by Administrator on 2019/2/14.
 * 全V健康  咨询
 */
public class VmemberConsult {

    private Integer id;
    private String patientWxopenid;//患者
    private String doctorWxopenid;//医生
    private String createTime;//订单时间
    private String serverTime;//接单时间
    private String completeTime;//服务完成时间
    private int type;//咨询类型 1次  2 3 4

    private String title;//咨询标题
    private String label;//咨询标签 甲状腺 乳腺
    private String picdesc;//图片描述 @>|<@ 分割
    private Integer dealStatus;//处理 1 等待接单 2等待服务完成 3服务完成
    private String assess;//服务评价
    private String assessDecode;//服务评价 解码之后的  使用时候用
    private String assessEncode;//服务评价 编码之后的 存数据库用
    private Integer cost;//费用

    private String doctorRealname;//医生姓名
    private String doctorRealavatars;//医生头像
    private String doctorContact;//医生联系
    private String doctorHospital;//坐诊医院

    private String patientRealname;//患者姓名
    private String patientContact;//患者联系
    private String patientRealavatars;//患者头像
    private Integer patientGender;//患者性别
    private String patientBirthday;//患者生日

    private Integer patnoread;
    private Integer docnoread;
    private Integer patonline;
    private Integer doconline;

    public String getDoctorContact() {
        return doctorContact;
    }

    public void setDoctorContact(String doctorContact) {
        this.doctorContact = doctorContact;
    }

    public String getPatientContact() {
        return patientContact;
    }

    public void setPatientContact(String patientContact) {
        this.patientContact = patientContact;
    }

    public String getAssessDecode() {
        //base64编码 解码
        if(assess!=null){
            return new String(Base64.decodeBase64(assess));
        }else{
            return assess;
        }
    }

    public String getAssessEncode() {
        //base64编码
        if(assess!=null){
            return Base64.encodeBase64String(assess.getBytes());
        }
        return null;
    }

    public Integer getPatientGender() {
        return patientGender;
    }
    public String getSex() {
        if(patientGender!=null){
            return patientGender==1?"男":"女";
        }
        return null;
    }
    //年龄 ——————根据出生日期计算
    public Integer getAge() {
        if(patientBirthday!=null){
            return CalculateUtil.getAge(patientBirthday);
        }
        return 0;
    }

    public void setPatientGender(Integer patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientBirthday() {
        return patientBirthday;
    }

    public void setPatientBirthday(String patientBirthday) {
        this.patientBirthday = patientBirthday;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getPatonline() {
        return patonline;
    }

    public void setPatonline(Integer patonline) {
        this.patonline = patonline;
    }

    public Integer getDoconline() {
        return doconline;
    }

    public void setDoconline(Integer doconline) {
        this.doconline = doconline;
    }

    private List<VmemberConsultLog> consultLogs;//聊天信息

    public Integer getPatnoread() {
        return patnoread;
    }

    public void setPatnoread(Integer patnoread) {
        this.patnoread = patnoread;
    }

    public Integer getDocnoread() {
        return docnoread;
    }

    public void setDocnoread(Integer docnoread) {
        this.docnoread = docnoread;
    }

    @Override
    public String toString() {
        return "VmemberConsult{" +
                "id=" + id +
                ", patientWxopenid='" + patientWxopenid + '\'' +
                ", doctorWxopenid='" + doctorWxopenid + '\'' +
                ", createTime='" + createTime + '\'' +
                ", serverTime='" + serverTime + '\'' +
                ", completeTime='" + completeTime + '\'' +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", label='" + label + '\'' +
                ", picdesc='" + picdesc + '\'' +
                ", dealStatus=" + dealStatus +
                ", assess='" + assess + '\'' +
                ", cost=" + cost +
                ", doctorRealname='" + doctorRealname + '\'' +
                ", doctorRealavatars='" + doctorRealavatars + '\'' +
                ", doctorHospital='" + doctorHospital + '\'' +
                ", patientRealname='" + patientRealname + '\'' +
                ", patientRealavatars='" + patientRealavatars + '\'' +
                ", patnoread=" + patnoread +
                ", docnoread=" + docnoread +
                ", patonline=" + patonline +
                ", doconline=" + doconline +
                ", consultLogs=" + consultLogs +
                ", oid=" + oid +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDoctorRealavatars() {
        return doctorRealavatars;
    }

    public void setDoctorRealavatars(String doctorealavatars) {
        this.doctorRealavatars = doctorealavatars;
    }

    public String getDoctorHospital() {
        return doctorHospital;
    }

    public void setDoctorHospital(String doctorHospital) {
        this.doctorHospital = doctorHospital;
    }

    public String getPatientRealname() {
        return patientRealname;
    }

    public void setPatientRealname(String patientRealname) {
        this.patientRealname = patientRealname;
    }

    public String getPatientRealavatars() {
        return patientRealavatars;
    }

    public void setPatientRealavatars(String patientRealavatars) {
        this.patientRealavatars = patientRealavatars;
    }

    public List<VmemberConsultLog> getConsultLogs() {
        return consultLogs;
    }

    public void setConsultLogs(List<VmemberConsultLog> consultLogs) {
        this.consultLogs = consultLogs;
    }

    private Integer oid;//项目id_涉及到余额所以需要辅助

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getDoctorRealname() {
        return doctorRealname;
    }

    public void setDoctorRealname(String doctorRealname) {
        this.doctorRealname = doctorRealname;
    }

    public String getPatientWxopenid() {
        return patientWxopenid;
    }

    public void setPatientWxopenid(String patientWxopenid) {
        this.patientWxopenid = patientWxopenid;
    }

    public String getDoctorWxopenid() {
        return doctorWxopenid;
    }

    public void setDoctorWxopenid(String doctorWxopenid) {
        this.doctorWxopenid = doctorWxopenid;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        //去掉timestamp后面的0
        this.createTime = createTime.substring(0,createTime.lastIndexOf("."));
    }

    public String getServerTime() {
        //返回日期时间格式
        if(serverTime!= null){
            return  CalculateUtil.timestampTodatetime(Long.valueOf(serverTime));
        }else{
            return  null;
        }
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public String getCompleteTime() {

        if(completeTime!= null){

            return CalculateUtil.timestampTodatetime(Long.valueOf(completeTime));

        }else{
            return  null;
        }

    }
    public String getServerTimeWithTimestamp() {
        return serverTime;
    }
    public String getCompleteTimeWithTimestamp() {
       return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPicdesc() {
        return picdesc;
    }

    public void setPicdesc(String picdesc) {
        this.picdesc = picdesc;
    }

    public Integer getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(Integer dealStatus) {
        this.dealStatus = dealStatus;
    }

    public String getAssess() {
        return assess;
    }

    public void setAssess(String assess) {
        this.assess = assess;
    }
}
