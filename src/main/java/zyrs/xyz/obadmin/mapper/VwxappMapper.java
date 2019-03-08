package zyrs.xyz.obadmin.mapper;

import org.apache.ibatis.annotations.Mapper;
import zyrs.xyz.obadmin.bean.VmemberConsult;
import zyrs.xyz.obadmin.bean.VmemberPatient;
import zyrs.xyz.obadmin.bean.WxappMember;

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

    Double getPatientConsultSumMoney(String wxopenid);


    void modifyUserInfoDoctorByWxopenid(WxappMember wxappMembe);

    void modifyUserInfoBase(WxappMember wxappMember);

    WxappMember getUserInfoById(Integer id);


    void createConsultOrder(VmemberConsult vmemberConsult);

    void updateMemberIdentityByWxopenid(String patientWxopenid, int i);

    List<VmemberConsult> getPatientConsultList(String wxopenid,int oid);
}
