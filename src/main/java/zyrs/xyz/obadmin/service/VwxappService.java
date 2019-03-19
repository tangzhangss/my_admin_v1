package zyrs.xyz.obadmin.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zyrs.xyz.obadmin.bean.*;
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

        WxappMember wxappMember = null;
        wxappMember = vwxappMapper.getDoctorDetail(id);

        wxappMember.setPatientNum(vwxappMapper.getDoctorPatientNum(wxappMember.getWxopenid()));

        return wxappMember;
    }
    public WxappMember getDoctorDetailByWxopenid(String wxopenid) {
        return  vwxappMapper.getDoctorDetailByWxopenid(wxopenid);
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

    public Double getPatientConsultSumMoney(String wxopenid, int oid) {
        return vwxappMapper.getPatientConsultSumMoney(wxopenid,oid);
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

    public String createConsultOrderMeal(VmemberConsult vmemberConsult) {
        //查询余额
        Double balance =wxappMapper.getMemberBlanceByOpenid(vmemberConsult.getPatientWxopenid(),vmemberConsult.getOid());
        balance = balance==null?0:balance;
        //比较余额
        Double money = balance - vmemberConsult.getCost();
        if(money < 0){
            return "余额不足,请先在‘个人中心’-> ‘充值’之后再进行咨询";
        }else{
            //更改余额
            wxappMapper.updateMemberBlanceByOpenid(vmemberConsult.getPatientWxopenid(),vmemberConsult.getOid(),money);

            //设置服务时间
            vmemberConsult.setServerTime(String.valueOf(System.currentTimeMillis()));
            //创建订单
            vwxappMapper.createConsultOrderMeal(vmemberConsult);
            //改变用户身份为患者
            vwxappMapper.updateMemberIdentityByWxopenid(vmemberConsult.getPatientWxopenid(),2);
        }

        return  "套餐咨询订单生成成功,您可以返回首页，进入‘健康咨询’咨询医生了";
    }

    public List<VmemberConsult> getPatientConsultList(String wxopenid,int oid) {
        return   vwxappMapper.getPatientConsultList(wxopenid,oid);
    }
    public List<VmemberConsult> getPatientConsultCompletedList(String wxopenid,int oid) {
        return   vwxappMapper.getPatientConsultCompletedList(wxopenid,oid);
    }
    public List<VmemberConsult> getDoctorConsultList(String wxopenid) {
        return   vwxappMapper.getDoctorConsultList(wxopenid);
    }
    public List<VmemberConsult> getDoctorConsultCompleteList(String wxopenid) {
        return   vwxappMapper.getDoctorConsultCompleteList(wxopenid);
    }
    public WxappMember getDoctorBaseInfoByWxopenid(String wxopenid) {
        return   vwxappMapper.getDoctorBaseInfoByWxopenid(wxopenid);
    }

    public void applyBecomeDoctor(WxappMember wxappMember) {
        vwxappMapper.applyBecomeDoctor(wxappMember);
    }

    public void setMemberIdentityWithDoctor(String wxopenid) {
        vwxappMapper.setMemberIdentityWithDoctor(wxopenid);
    }

    public List<VmemberConsult> getConsultNewList() {
        return vwxappMapper.getConsultNewList();
    }

    public VdoctorBalance getDoctorBalanceDetail(String wxopenid) {
        return vwxappMapper.getDoctorBalanceDetail(wxopenid);
    }

    @Transactional
    public void doctorReceiptConsult(String wxopenid, Integer id) {
         //修改咨询订单 医生wxopenid  服务开始时间 状态
        long datestamp = System.currentTimeMillis();

        vwxappMapper.doctorReceiptConsult(wxopenid,id,datestamp);
    }


    public VmemberConsult getConsultDetailById(Integer id) {
        return vwxappMapper.getConsultDetailById(id);
    }

    public void setConsultMessageReadedById(Integer id, int identity) {
        if(identity == 2){
            vwxappMapper.setConsultPatientReadedById(id);
        }else if(identity == 3){
            vwxappMapper.setConsultDoctorReadedById(id);
        }
    }

    public void sendChatMessage(VmemberConsultLog vmemberConsultLog){
        vwxappMapper.sendChatMessage(vmemberConsultLog);

        //未读消息+1
        if(vmemberConsultLog.getIdentity() == 3){
            vwxappMapper.setConsultPatientNOReadAdd(vmemberConsultLog.getConsultId());
        }else if(vmemberConsultLog.getIdentity() == 2){
            vwxappMapper.setConsultDoctorNOReadAdd(vmemberConsultLog.getConsultId());
        }

        //检查服务是否完成
        //1.次数
        vwxappMapper.checkConsultStatusIsCompleteCount(vmemberConsultLog.getConsultId(),System.currentTimeMillis());
        //2.时间
        vwxappMapper.checkConsultStatusIsCompleteTime(vmemberConsultLog.getConsultId(),System.currentTimeMillis());
    }

    public void setConsultAssess(VmemberConsult vmemberConsult) {
        vwxappMapper.setConsultAssess(vmemberConsult.getId(),vmemberConsult.getAssessEncode());
    }

    public Vdoctor getDoctorHomeByWxopenid(String wxopenid) {

        Vdoctor doctor = new Vdoctor();

        doctor.setWxappMember(vwxappMapper.getDoctorDetailByWxopenid(wxopenid));
        doctor.setPatnum(vwxappMapper.getDoctorPatientNum(wxopenid));
        doctor.setVmemberConsults(vwxappMapper.getDoctorConsultCompleteList(wxopenid));
        //套餐
        doctor.setVdoctorMeals(vwxappMapper.getDoctorConsultMeal(wxopenid));

        return doctor;
    }

    public List<WxappMember> userInfoAndMydoctor(String wxopenid) {
        return vwxappMapper.userInfoAndMydoctor(wxopenid);
    }

    public List<VdoctorMeal> getDoctorConsultMeal(String wxopenid) {
        return vwxappMapper.getDoctorConsultMeal(wxopenid);
    }

    public void deleteDoctorConsultMeal(String wxopenid) {
        vwxappMapper.deleteDoctorConsultMeal(wxopenid);
    }

    public void updateDoctorConsultMeal(VdoctorMeal vdoctorMeal) {
        vwxappMapper.updateDoctorConsultMeal(vdoctorMeal);
    }

    public List<VmemberConsult> getConsultOrderList(String like, Integer oid, Integer status) {
            like = '%'+like+'%';
           if(status == 1){
               return vwxappMapper.getConsultNewList();
           }else{
               return vwxappMapper.getConsultOrderList(like,oid,status);
           }

    }
}
