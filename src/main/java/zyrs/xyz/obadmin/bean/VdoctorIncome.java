package zyrs.xyz.obadmin.bean;

/**
 * Created by Administrator on 2019/3/20.
 *
 * 医生收益结算类
 */
public class VdoctorIncome {
    private String realname;//医生姓名
    private String realAvatars;//医生头像
    private WxappBank wxappBank;//银行信息
    private VdoctorBalance vdoctorBalance;//收益明细

    public String getRealAvatars() {
        return realAvatars;
    }

    public void setRealAvatars(String realAvatars) {
        this.realAvatars = realAvatars;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public WxappBank getWxappBank() {
        return wxappBank;
    }

    public void setWxappBank(WxappBank wxappBank) {
        this.wxappBank = wxappBank;
    }

    public VdoctorBalance getVdoctorBalance() {
        return vdoctorBalance;
    }

    public void setVdoctorBalance(VdoctorBalance vdoctorBalance) {
        this.vdoctorBalance = vdoctorBalance;
    }
}
