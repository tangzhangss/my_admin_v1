package zyrs.xyz.obadmin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import zyrs.xyz.obadmin.bean.*;
import zyrs.xyz.obadmin.service.ObService;
import zyrs.xyz.obadmin.service.VwxappService;
import zyrs.xyz.obadmin.service.WxappService;
import zyrs.xyz.obadmin.utils.HttpRequest;
import zyrs.xyz.obadmin.utils.WxappApiUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@SessionAttributes({"current_user","menuList","statistics"})
@Controller
@RequestMapping("/wxapp_v")
public class VwxappController {

    @Autowired
    private VwxappService vWxappService;
    @Autowired
    private WxappService wxappService;

    /**
     * 渲染  所有用户信息
     * like 模糊查询
     * pageNo
     * @return  渲染页面路径
     */
    @RequestMapping("alluser")
    public  String renderAllUser(Map<String,Object> map,@RequestParam(value="like",defaultValue = "")String like,@RequestParam(value="pageNo",defaultValue = "1")Integer pageNo){
        User user = (User) map.get("current_user");

        PageHelper.startPage(pageNo, 10);
        //获取所有的用户信息
        List<WxappMember> wxappMembers = vWxappService.getAllUserBaseInfo(like,user.getObId());

        PageInfo<?> appsPageInfo = new PageInfo<>(wxappMembers);

        map.put("members",wxappMembers);

        map.put("pageinfo",appsPageInfo);

        return "/v/user";
    }


    /**
     * 渲染  所有医生信息
     * like 模糊查询
     * pageNo
     * @return  渲染页面路径
     */
    @RequestMapping("doctor_list")
    public  String renderDoctorList(Map<String,Object> map,@RequestParam(value="like",defaultValue = "")String like,@RequestParam(value="pageNo",defaultValue = "1")Integer pageNo){
        User user = (User) map.get("current_user");

        PageHelper.startPage(pageNo, 10);
        //获取所有的用户信息
        List<WxappMember> wxappMembers = vWxappService.getDoctorList(like,user.getObId());

        PageInfo<?> appsPageInfo = new PageInfo<>(wxappMembers);

        map.put("members",wxappMembers);

        map.put("pageinfo",appsPageInfo);

        return "/v/doctor_list";
    }
    /**
     * 渲染  医生详情页
     * @return  渲染页面路径
     */
    @RequestMapping("doctor_detail")
    public  String renderDoctorDetail(Map<String,Object> map,@RequestParam(value="id")Integer id){
        //获取所有的用户信息
        WxappMember doctor = vWxappService.getDoctorDetail(id);

        map.put("doctor",doctor);

        return "/v/doctor_detail";
    }

    /**
     * 渲染  医生详情页
     * @return  渲染页面路径
     */
    @RequestMapping("doctor_access_apply")
    public  String renderDoctorAccessApply(Map<String,Object> map,@RequestParam(value="id")Integer id){

        //医生通过审核请求
        vWxappService.doctorAccessApply(id);

        return "redirect:doctor_detail?id="+id;
    }
    /**
     * 渲染  医生详情页
     * @return  渲染页面路径
     */
    @RequestMapping("doctor_refuse_apply")
    public  String renderDoctorRefuseApply(Map<String,Object> map,@RequestParam(value="id")Integer id,@RequestParam(value="message")String message){

        //医生通过审核请求
        vWxappService.doctorRefuseApply(id,message);

        return "redirect:doctor_detail?id="+id;
    }


    /**
     * 渲染  所有患者信息
     * like 模糊查询
     * pageNo
     * @return  渲染页面路径
     */
    @RequestMapping("patient_list")
    public  String renderPatientList(Map<String,Object> map,@RequestParam(value="like",defaultValue = "")String like,@RequestParam(value="pageNo",defaultValue = "1")Integer pageNo){
        User user = (User) map.get("current_user");
        PageHelper.startPage(pageNo, 10);
        //获取所有的用户信息
        List<VmemberPatient> vmemberPatients = vWxappService.getPatientList(like,user.getObId());

        PageInfo<?> appsPageInfo = new PageInfo<>(vmemberPatients);

        map.put("vmemberPatients",vmemberPatients);

        map.put("pageinfo",appsPageInfo);

        return "/v/patient_list";
    }
    /**
     * 渲染  患者详情页
     * @return  渲染页面路径
     */
    @RequestMapping("patient_detail")
    public  String renderPatientDetail(Map<String,Object> map,@RequestParam(value="id")Integer id){
        //获取用户信息
        WxappMember patient = vWxappService.getPatientInfo(id);
        //获取咨询记录  什么时候咨询了什么人  完后时间  评价 咨询标签
        List<VmemberConsult> patientConsultLog = vWxappService.getPatientConsultLog(patient.getWxopenid());

        //当前项目id
        int oid = ((User)map.get("current_user")).getObId();

        //获取总花费金额 包含余额.....
        Double cost =vWxappService.getPatientConsultSumMoney(patient.getOpenid(),oid);
       //获取用户余额
        Double balance = wxappService.getMemberBlanceByOpenidAndOid(patient.getWxopenid(),oid);

        map.put("patient",patient);
        map.put("patientConsultLog",patientConsultLog);
        map.put("cost",cost==null?0:cost);
        map.put("balance",balance==null?0:balance);

        return "/v/patient_detail";
    }
    /**
     * 获取咨询订单
     * @param status 状态 1 带接单 2 进行中 3完成 4退款
     * @param like 模糊查询 患者名字 患者手机 医生名字 医生手机
     * @return
     */
    @RequestMapping("consult_order")
    public  String consultWait(Map<String,Object> map,@RequestParam(value="like",defaultValue = "")String like
            ,@RequestParam(value="pageNo",defaultValue = "1")Integer pageNo
           ,@RequestParam(value="status")Integer status){

        User user = (User) map.get("current_user");
        PageHelper.startPage(pageNo, 10);
        //获取所有的用户信息
        List<VmemberConsult> vmemberConsults = vWxappService.getConsultOrderList(like,user.getObId(),status);

        PageInfo<?> appsPageInfo = new PageInfo<>(vmemberConsults);

        //一对多的时候 总数为错误  会算上所有条数
        map.put("vmemberConsults",vmemberConsults);

        map.put("pageinfo",appsPageInfo);

        map.put("status",status);

        return "/v/consult_order";
    }

    /**
     * 医生收益明细
     *
     * 所有医生....不管平台
     * @return
     */
    @RequestMapping("doctor_account")
    public String doctorIncomes(Map<String,Object> map,@RequestParam(value="like",defaultValue = "")String like){

        User user = (User) map.get("current_user");

        map.put("vdoctorincomes",vWxappService.getDoctorIncomes(like,user.getObId()));


        return "/v/doctor_account";
    }

    /**
     * 患者充值记录
     * @return
     */
    @RequestMapping("patient_recharge")
    public String patientRecharge(Map<String,Object> map,@RequestParam(value="like",defaultValue = "")String like ,@RequestParam(value="pageNo",defaultValue = "1")Integer pageNo){

        User user = (User) map.get("current_user");


        PageHelper.startPage(pageNo, 10);
        //获取所有的用户信息
        List<WeixinOrder> weixinOrders = vWxappService.getUserOrder(like,user.getObId());

        PageInfo<?> appsPageInfo = new PageInfo<>(weixinOrders);

        //一对多的时候 总数为错误  会算上所有条数

        map.put("weixinOrders",weixinOrders);

        map.put("pageinfo",appsPageInfo);


        return "/v/patient_recharge";
    }


    @ResponseBody
    @RequestMapping("refund_consult")
    public String refundConsult(@RequestParam("id")Integer id,Map<String,Object> map){
        User user = (User) map.get("current_user");

        try{
            vWxappService.refundConsult(id,user.getObId());
        }catch (Exception e){
            e.printStackTrace();
            return "退款失败!";
        }

        return "0";
    }

}
