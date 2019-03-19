package zyrs.xyz.obadmin.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/15.
 *
 * 全v健康  医生主页
 */
public class Vdoctor {
    private WxappMember wxappMember;//基本信息
    private Integer patnum;//患者人数
    private List<VmemberConsult> vmemberConsults;//咨询列表
    private List<VdoctorMeal> vdoctorMeals;                 //服务套餐

    public WxappMember getWxappMember() {
        return wxappMember;
    }

    public void setWxappMember(WxappMember wxappMember) {
        this.wxappMember = wxappMember;
    }

    public Integer getPatnum() {
        return patnum;
    }

    public void setPatnum(Integer patnum) {
        this.patnum = patnum;
    }

    public List<VmemberConsult> getVmemberConsults() {
        return vmemberConsults;
    }

    public void setVmemberConsults(List<VmemberConsult> vmemberConsults) {
        this.vmemberConsults = vmemberConsults;
    }

    public List<VdoctorMeal> getVdoctorMeals() {
        return vdoctorMeals;
    }

    public void setVdoctorMeals(List<VdoctorMeal> vdoctorMeals) {
        this.vdoctorMeals = vdoctorMeals;
    }
}
