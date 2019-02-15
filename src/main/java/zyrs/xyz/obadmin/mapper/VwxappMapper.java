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
    List<WxappMember> getAllUserBaseInfo(String wxopenid,String nickname);

    List<WxappMember> getDoctorList(String like);

    WxappMember getDoctorDetail(Integer id);

    void doctorAccessApply(Integer id);

    void doctorRefuseApply(Integer id, String message);

    List<VmemberPatient> getPatientList(String like);

    WxappMember getPatientInfo(Integer id);

    List<VmemberConsult> getPatientConsultLog(String wxopenid);

    Double getPatientConsultSumMoney(String wxopenid);
}
