package zyrs.xyz.obadmin.mapper;

import org.apache.ibatis.annotations.Mapper;
import zyrs.xyz.obadmin.bean.*;

import java.util.List;

/**
 * Created by Administrator on 2019/2/11.
 */
@Mapper
public interface VwxappMapper {
    List<WxappMember> getAllUserBaseInfo(String wxopenid,String nickname,Integer oid);

    List<WxappMember> getDoctorList(String like,Integer oid);

    WxappMember getDoctorDetail(Integer id);

    void doctorAccessApply(Integer id);

    void doctorRefuseApply(Integer id, String message);

    List<VmemberPatient> getPatientList(String like,Integer oid);

    WxappMember getPatientInfo(Integer id);

    List<VmemberConsult> getPatientConsultLog(String wxopenid);

    Double getPatientConsultSumMoney(String wxopenid, int oid);


    void modifyUserInfoDoctorByWxopenid(WxappMember wxappMembe);

    void modifyUserInfoBase(WxappMember wxappMember);

    WxappMember getUserInfoById(Integer id);


    void createConsultOrder(VmemberConsult vmemberConsult);

    void updateMemberIdentityByWxopenid(String patientWxopenid, int i);

    List<VmemberConsult> getPatientConsultList(String wxopenid,int oid);
    List<VmemberConsult> getPatientConsultCompletedList(String wxopenid,int oid);
    List<VmemberConsult> getDoctorConsultList(String openid);
    List<VmemberConsult> getDoctorConsultCompleteList(String openid);

    WxappMember getDoctorBaseInfoByWxopenid(String wxopenid);

    void applyBecomeDoctor(WxappMember wxappMember);

    void setMemberIdentityWithDoctor(String wxopenid);

    List<VmemberConsult> getConsultNewList();

    VdoctorBalance getDoctorBalanceDetail(String wxopenid);

    void doctorReceiptConsult(String wxopenid, Integer id, long datestamp);

    Integer getDoctorPatientNum(String wxopenid);

    WxappMember getDoctorDetailByWxopenid(String wxopenid);

    VmemberConsult getConsultDetailById(Integer id);

    void setConsultDoctorReadedById(Integer id);

    void setConsultPatientReadedById(Integer id);

    void sendChatMessage(VmemberConsultLog vmemberConsultLog);

    void checkConsultStatusIsCompleteCount(Integer id, long datetime);

    void checkConsultStatusIsCompleteTime(Integer id,long datetime);

    void setConsultPatientNOReadAdd(Integer id);

    void setConsultDoctorNOReadAdd(Integer id);

    void setConsultAssess(Integer id, String assessEncode);

    List<VdoctorMeal> getDoctorConsultMeal(String wxopenid);

    void createConsultOrderMeal(VmemberConsult vmemberConsult);

    List<WxappMember> userInfoAndMydoctor(String wxopenid);

    void deleteDoctorConsultMeal(String wxopenid);

    void updateDoctorConsultMeal(VdoctorMeal vdoctorMeal);

    List<VmemberConsult> getConsultOrderList(String like, Integer oid, Integer status);
}
