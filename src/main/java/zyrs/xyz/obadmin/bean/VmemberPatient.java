package zyrs.xyz.obadmin.bean;

/**
 * Created by Administrator on 2019/2/14.
 *
 * 患者实体类
 *
 * 有患者自己的信息也有其咨询医生的信息
 */
public class VmemberPatient {

    private WxappMember patient;

    private WxappMember doctor;

    public WxappMember getPatient() {
        return patient;
    }

    public void setPatient(WxappMember patient) {
        this.patient = patient;
    }

    public WxappMember getDoctor() {
        return doctor;
    }

    public void setDoctor(WxappMember doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "VmemberPatient{" +
                "patient=" + patient +
                ", doctor=" + doctor +
                '}';
    }
}
