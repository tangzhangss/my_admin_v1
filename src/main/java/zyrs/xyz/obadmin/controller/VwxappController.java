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
        //获取订单话费总金额.
        Double cost =vWxappService.getPatientConsultSumMoney(patient.getWxopenid());

        map.put("patient",patient);
        map.put("patientConsultLog",patientConsultLog);
        map.put("cost",cost==null?0:cost);

        return "/v/patient_detail";
    }
}
