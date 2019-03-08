package zyrs.xyz.obadmin.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zyrs.xyz.obadmin.bean.VmemberConsult;
import zyrs.xyz.obadmin.bean.VmemberPatient;
import zyrs.xyz.obadmin.bean.WxappMember;
import zyrs.xyz.obadmin.mapper.VwxappMapper;
import zyrs.xyz.obadmin.mapper.WxappMapper;

import java.util.List;

/**
 * Created by Administrator on 2019/2/11.
 */
@Service
public class VwxappService {

    @Autowired
    private VwxappMapper vwxappMapper;
    @Autowired
    private WxappMapper wxappMapper;

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

    public void modifyUserInfo(WxappMember wxappMember) {

        vwxappMapper.modifyUserInfoBase(wxappMember);

        if(wxappMember.getIdentity()==3){
            vwxappMapper.modifyUserInfoDoctorByWxopenid(wxappMember);
        }
    }


    public WxappMember getUserInfoById(Integer id) {
        return   vwxappMapper.getUserInfoById(id);
    }

    @Transactional
    public String createConsultOrder(VmemberConsult vmemberConsult) {
        //查询余额
        Double balance =wxappMapper.getMemberBlanceByOpenid(vmemberConsult.getPatientWxopenid(),vmemberConsult.getOid());
        balance = balance==null?0:balance;
        //比较余额
        Double money = balance - vmemberConsult.getCost();
        if(money < 0){
            return "余额不足,请先在‘个人中心’-> ‘充值’之后再进行咨询，您的咨询信息将会保留。";
        }else{
          //更改余额
          wxappMapper.updateMemberBlanceByOpenid(vmemberConsult.getPatientWxopenid(),vmemberConsult.getOid(),money);
          //创建订单
          vwxappMapper.createConsultOrder(vmemberConsult);
          //改变用户身份为患者
          vwxappMapper.updateMemberIdentityByWxopenid(vmemberConsult.getPatientWxopenid(),2);
        }

        return  "订单生成成功，请注意查看微信消息，我们将尽快为您匹配医生" ;
    }

    public List<VmemberConsult> getPatientConsultList(String wxopenid,int oid) {
        return   vwxappMapper.getPatientConsultList(wxopenid,oid);
    }
}
