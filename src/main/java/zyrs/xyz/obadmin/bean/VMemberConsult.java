package zyrs.xyz.obadmin.bean;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by Administrator on 2019/2/14.
 * 全V健康  咨询
 */
public class VmemberConsult {

    private String createTime;//订单时间


    private String serverTime;//接单时间


    private String completeTime;//服务完成时间

    private String title;//咨询标题
    private String label;//咨询标签 甲状腺 乳腺
    private String picdesc;//图片描述 @>|<@ 分割
    private Integer dealStatus;//处理 1 等待接单 2等待服务完成 3服务完成
    private String assess;//服务评价

    private String doctorRealname;//医生姓名

    public String getDoctorRealname() {
        return doctorRealname;
    }

    public void setDoctorRealname(String doctorRealname) {
        this.doctorRealname = doctorRealname;
    }

    @Override
    public String toString() {
        return "VmemberConsult{" +
                "createTime='" + createTime + '\'' +
                ", serverTime='" + serverTime + '\'' +
                ", completeTime='" + completeTime + '\'' +
                ", title='" + title + '\'' +
                ", label='" + label + '\'' +
                ", picdesc='" + picdesc + '\'' +
                ", dealStatus=" + dealStatus +
                ", assess='" + assess + '\'' +
                ", doctorRealname='" + doctorRealname + '\'' +
                '}';
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        //去掉timestamp后面的0
        this.createTime = createTime.substring(0,createTime.lastIndexOf(":"));
    }

    public String getServerTime() {
        return serverTime==null?"未接单":serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public String getCompleteTime() {
        return completeTime==null?"未完成":completeTime;
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
