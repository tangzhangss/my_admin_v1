package zyrs.xyz.obadmin.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zyrs.xyz.obadmin.bean.VmemberConsult;
import zyrs.xyz.obadmin.bean.VmemberPatient;
import zyrs.xyz.obadmin.bean.WxappMember;
import zyrs.xyz.obadmin.mapper.VwxappMapper;

import java.util.List;

/**
 * Created by Administrator on 2019/2/11.
 */
@Service
public class VwxappService {

    @Autowired
    private VwxappMapper vwxappMapper;

    public List<WxappMember> getAllUserBaseInfo(String like,Integer oid) {

        //数据库是base64编码之后的 所以需要编码之后查询
        String nickname =like.equals("")?"%%":"%"+new String(Base64.encodeBase64(like.getBytes()))+"%";

        return vwxappMapper.getAllUserBaseInfo(like,nickname,oid);
    }

    public List<WxappMember> getDoctorList(String like,Integer oid) {

        like = "%"+like+"%";

        return vwxappMapper.getDoctorList(like,oid);
    }

    public WxappMember getDoctorDetail(Integer id) {
        return vwxappMapper.getDoctorDetail(id);
    }

    public void doctorAccessApply(Integer id) {
        vwxappMapper.doctorAccessApply(id);
    }

    public void doctorRefuseApply(Integer id, String message) {
        vwxappMapper.doctorRefuseApply(id,message);
    }

    public List<VmemberPatient> getPatientList(String like,Integer oid){
        like = "%"+like+"%";
        return vwxappMapper.getPatientList(like,oid);
    }

    public WxappMember getPatientInfo(Integer id) {
        return vwxappMapper.getPatientInfo(id);
    }

    public List<VmemberConsult> getPatientConsultLog(String wxopenid) {
        return vwxappMapper.getPatientConsultLog(wxopenid);
    }

    public Double getPatientConsultSumMoney(String wxopenid) {
        return vwxappMapper.getPatientConsultSumMoney(wxopenid);
    }
}
