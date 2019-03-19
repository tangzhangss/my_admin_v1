package zyrs.xyz.obadmin.controller;

import com.sun.tools.javac.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import zyrs.xyz.obadmin.bean.*;
import zyrs.xyz.obadmin.service.VwxappService;
import zyrs.xyz.obadmin.service.WeixinService;
import zyrs.xyz.obadmin.service.WxappService;
import zyrs.xyz.obadmin.utils.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/3/1.
 */
@RestController
@RequestMapping("/api/v")
public class VwxappApiController {

    @Autowired
    private WxappService wxappService;
    @Autowired
    private VwxappService vwxappService;
    @Autowired
    private WeixinService weixinService;

    /**
     * 用户授权登录,
     * @param wxappMember 基本信息
     * @param code 用户登录凭证
     * @return
     */
    @RequestMapping("login")
    public WxappMember login(WxappMember wxappMember,@RequestParam("code")String code){

        Wxapp wxapp = wxappService.getWxappInfoByObId(wxappMember.getOid());

        WxappResult wxappResult = WxappApiUtil.authLogin(wxapp.getAppid(),wxapp.getSecret(),code);
        System.out.println(wxappResult);
        wxappMember.setOid(wxappMember.getOid());
        wxappMember.setOpenid(wxappResult.getOpenid());
        //根据openID 更新或者插入数据 并返回 数据

        WxappMember member = wxappService.insertOrUpdateMemberAndReturnData(wxappMember);


        return member;
    }


    /**
     * 返回用户的微信公众号openid
     * @param id 用户id
     * @return
     */
    @RequestMapping("get_user_wxopenid_by_id")
    public String getUserWxopenidById(@RequestParam("id")Integer id){

        String member = wxappService.getUserWxopenidById(id);

        return member;
    }


    /**
     * 设置用户上线状态  在线 离线
     * @param id  用户id
     * @param online   1在线 2离线
     */
    @RequestMapping("set_member_is_online")
    public void setMemberIsOnline(@RequestParam("id")Integer id,@RequestParam("online")int online){
          wxappService.setMemberIsOnline(id,online);
    }

    /**
     * 返回用户信息
     * @param id 用户id
     * @return
     */
    @RequestMapping("get_userinfo")
    public WxappMember getUserInfoById(@RequestParam("id")Integer id){

        WxappMember member = vwxappService.getUserInfoById(id);

        return member;
    }

    /**
     * 上传图片
     * @param id  用户id
     * @param file  文件
     * @return  文件地址
     */
    @RequestMapping("upload_picture")
    public String uploadPicture(@RequestParam("id")Integer id,@RequestParam("file") MultipartFile file){

        String res =  AliyunOss.upload_picture("wxapp/v/"+id,file);
        return res;
    }

    /**
     * 更新用户信息
     * 真实姓名 生日 联系方式  +》医院  教育  领域  （医生）
     * 通过用户id
     * @param wxappMember
     * @return
     */
    @RequestMapping("modify_userinfo")
    public int modifyUserInfo(WxappMember wxappMember){
       try{
           vwxappService.modifyUserInfo(wxappMember);
       }catch (Exception e){
           System.out.println(e);
           return 500;
       }

        return 0;
    }

    @RequestMapping("get_user_bank")
    public WxappBank getUserBank(@RequestParam("wxopenid")String wxopenid,@RequestParam("oid")Integer oid){
        return   wxappService.getUserBank(wxopenid,oid);
    }

    @RequestMapping("modify_user_bank")
    public int modifyUserBank(WxappBank wxappBank){
        try{
            //查询此用户绑定 id
            wxappService.modifyUserBank(wxappBank);
        }catch (Exception e){
            e.printStackTrace();
            return 500;
        }
        return 0;
    }

    /**
     * 统一下单接口 _次数用公众号信息支付 所以 appid也是公众号的
     * @param oid 项目id
     * @param request 请求信息 包含  wxopenid  amount 等
     * @return
     */
    @RequestMapping("/create_unified_order")
    public String createUnifiedOrder(@RequestParam("oid")Integer oid,HttpServletRequest request){

        //重置金额 约 下单金额不同 存在优惠
        String money = request.getParameter("money");

        try{
          //1.通过项目id获取项目的商户信息_公众号信息
            Wxapp wxapp = wxappService.getWxappInfoByObId(oid);
            WxappMerchant wxappMerchant = wxappService.getWxappMerchantInfo(oid);
//           //没有开通微信支付_设置一个开通了的_小程序已经没问题了，公众号未测试 暂时先这样
//             wxapp.setGzappid("wx7bac4e83ecf86d4b");
//             wxappMerchant.setMchid("1494443872");

            //商户订单号....外部设置...作为判断依据
            String outTradeNo = "V"+CalculateUtil.getCurrentDate("yyyyMMdd")+System.currentTimeMillis();

            String result = WeixinApiUtil.unifiedOrder(wxapp,wxappMerchant,outTradeNo,request,"http://localhost/api/v/payCallback",false);

            Map<String,String> resultmap = WeixinPayUtil.doXMLParse(result);

            System.out.println("结果:"+resultmap.get("return_code"));

            return result;

        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
    /**
     * 统一下单接口 _回调
     * @return
     */
    @RequestMapping("/pay_call_back")
    public void payCallback(HttpServletRequest request){
        System.out.println("下单回调:"+request);
    }


    /**
     * 创建咨询订单
     * @return  结果  success   "错误信息"
     */
    @RequestMapping("/create_consult_order")
    public String createConsultOrder(VmemberConsult vmemberConsult){

        String res ="";
        try{
           res =  vwxappService.createConsultOrder(vmemberConsult);
        }catch(Exception e){
            e.printStackTrace();
           res =  e.getMessage();
        }

        if(StringUtils.indexOfIgnoreCase(res,"成功") >= 0){
            //有生意了 通知管理者微信
            //购买成功通知 同时通知 管理者 和 购买用户

            //数据库获取模板id_管理者id_url   BY 项目id和模板id(自己制定不可变 为1)
            WeixinTemplate weixinTemplate = weixinService.getBuySuccessTemplateById("quanvjk_buy_success");

            if(weixinTemplate == null){
                return "订单生成成功，但消息通知失败，原因:服务器模板ID不存在，请联系客服检查是否配置错误！";
            }

           //填充数据
            WeixinTemplateValue t1 = new WeixinTemplateValue("全V健康咨询服务，金额:￥"+vmemberConsult.getCost(),"#ff0000");
            WeixinTemplateValue t2 = new WeixinTemplateValue("咨询内容:["+vmemberConsult.getLabel()+"]=>["+vmemberConsult.getTitle()+"]","#159bf4");

           //生成data_每个模板的参数都不一样，自动化转换暂时没想到好的方式这里先手动拼接
            StringBuffer str = new StringBuffer();
            str.append("{");
            str.append("\"name\":"+t1.toJson()+",");
            str.append("\"remark\":"+t2.toJson());
            str.append("}");

            weixinTemplate.setData(str.toString());
            //发送消息
            //获取公众号程序的appid srcret
            Wxapp wxapp = wxappService.getWxappInfoByObId(vmemberConsult.getOid());

            //发送给用户
            weixinTemplate.setTouser(vmemberConsult.getPatientWxopenid());
            WeixinResult weixinResult = WeixinApiUtil.sendTemplateMessage(weixinTemplate,wxapp);

            //发送失败 提示...
            if(weixinResult.getErrcode() != 0){
                return "订单生成成功，但消息通知失败，原因:"+weixinResult.getErrmsg()+"，请联系客服检查是否配置错误！";
            }
            /**
             * 发送给管理者
             */
           //获取咨询用户姓名 手机号
            WxappMember wxappMember = wxappService.getMemberBaseInfoByWxopenidAndOid(vmemberConsult.getPatientWxopenid(),vmemberConsult.getOid());

            WeixinTemplate weixinTemplate1 = weixinService.getBuySuccessTemplateById("quanvjk_consult_inform");
            //填充数据
            WeixinTemplateValue t11 = new WeixinTemplateValue("有客户提交了咨询订单,金额 ￥"+vmemberConsult.getCost(),"#ff0000");
            WeixinTemplateValue t22 = new WeixinTemplateValue(wxappMember.getRealname()+"   "+wxappMember.getContact(),"#777777");
            WeixinTemplateValue t33 = new WeixinTemplateValue(CalculateUtil.getCurrentDate("yyyy-MM-dd aa hh:mm"),"#777777");
            WeixinTemplateValue t44 = new WeixinTemplateValue("["+vmemberConsult.getLabel()+"]=>["+vmemberConsult.getTitle()+"]","#159bf4");
            WeixinTemplateValue t55 = new WeixinTemplateValue("全V健康","#777777");

            //生成data_每个模板的参数都不一样，自动化转换暂时没想到好的方式这里先手动拼接
            StringBuffer str1 = new StringBuffer();
            str1.append("{");
            str1.append("\"first\":"+t11.toJson()+",");
            str1.append("\"keyword1\":"+t22.toJson()+",");
            str1.append("\"keyword2\":"+t33.toJson()+",");
            str1.append("\"keyword3\":"+t44.toJson()+",");
            str1.append("\"remark\":"+t55.toJson());

            str1.append("}");

            weixinTemplate1.setData(str1.toString());
            weixinTemplate1.setTouser(weixinTemplate1.getAdminOpenid());

            WeixinResult weixinResult1 = WeixinApiUtil.sendTemplateMessage(weixinTemplate1,wxapp);

            System.out.println("购买成功模板消息发送结果:"+weixinResult1);
        }


        return res;
    }
    /**
     * 创建咨询订单_ 套餐
     * @return  结果  success   "错误信息"
     */
    @RequestMapping("/create_consult_order_meal")
    public String createConsultOrderMeal(VmemberConsult vmemberConsult,@RequestParam("monthnum")int monthnum){
        String res ="";

        //设置完成时间
        if(monthnum != 0){
            vmemberConsult.setCompleteTime(String.valueOf(CalculateUtil.getTimestampWithAfterMonth(monthnum)));
        }

        try{
            res =  vwxappService.createConsultOrderMeal(vmemberConsult);
        }catch(Exception e){
            e.printStackTrace();
            res =  e.getMessage();
        }

        if(StringUtils.indexOfIgnoreCase(res,"成功") >= 0){
            //有生意了 通知管理者微信
            //购买成功通知 同时通知 管理者 和 购买用户

            //数据库获取模板id_管理者id_url   BY 项目id和模板id(自己制定不可变 为1)
            WeixinTemplate weixinTemplate = weixinService.getBuySuccessTemplateById("quanvjk_buy_success");

            if(weixinTemplate == null){
                return "订单生成成功，但消息通知失败，原因:服务器模板ID不存在，请联系客服检查是否配置错误！";
            }

            //填充数据
            WeixinTemplateValue t1 = new WeixinTemplateValue("全V健康咨询服务，金额:￥"+vmemberConsult.getCost(),"#ff0000");
            WeixinTemplateValue t2 = new WeixinTemplateValue("咨询内容:套餐服务","#159bf4");

            //生成data_每个模板的参数都不一样，自动化转换暂时没想到好的方式这里先手动拼接
            StringBuffer str = new StringBuffer();
            str.append("{");
            str.append("\"name\":"+t1.toJson()+",");
            str.append("\"remark\":"+t2.toJson());
            str.append("}");

            weixinTemplate.setData(str.toString());
            //发送消息
            //获取公众号程序的appid srcret
            Wxapp wxapp = wxappService.getWxappInfoByObId(vmemberConsult.getOid());

            //发送给用户
            weixinTemplate.setTouser(vmemberConsult.getPatientWxopenid());
            WeixinResult weixinResult = WeixinApiUtil.sendTemplateMessage(weixinTemplate,wxapp);

            //发送失败 提示...
            if(weixinResult.getErrcode() != 0){
                return "订单生成成功，但消息通知失败，原因:"+weixinResult.getErrmsg()+"，请联系客服检查是否配置错误！";
            }
            /**
             * 发送给医生
             */
            //获取咨询用户姓名 手机号
            WxappMember wxappMember = wxappService.getMemberBaseInfoByWxopenidAndOid(vmemberConsult.getPatientWxopenid(),vmemberConsult.getOid());

            WeixinTemplate weixinTemplate1 = weixinService.getBuySuccessTemplateById("quanvjk_consult_inform");
            //填充数据
            WeixinTemplateValue t11 = new WeixinTemplateValue("有新患者购买了您的咨询套餐,金额 ￥"+vmemberConsult.getCost(),"#000000");
            WeixinTemplateValue t22 = new WeixinTemplateValue(wxappMember.getRealname()+"   "+wxappMember.getContact(),"#777777");
            WeixinTemplateValue t33 = new WeixinTemplateValue(CalculateUtil.getCurrentDate("yyyy-MM-dd aa hh:mm"),"#777777");
            WeixinTemplateValue t44 = new WeixinTemplateValue("套餐服务","#159bf4");
            WeixinTemplateValue t55 = new WeixinTemplateValue("线上咨询、问诊就上全V健康!","#ff0000");

            //生成data_每个模板的参数都不一样，自动化转换暂时没想到好的方式这里先手动拼接
            StringBuffer str1 = new StringBuffer();
            str1.append("{");
            str1.append("\"first\":"+t11.toJson()+",");
            str1.append("\"keyword1\":"+t22.toJson()+",");
            str1.append("\"keyword2\":"+t33.toJson()+",");
            str1.append("\"keyword3\":"+t44.toJson()+",");
            str1.append("\"remark\":"+t55.toJson());

            str1.append("}");

            weixinTemplate1.setData(str1.toString());
            weixinTemplate1.setTouser(vmemberConsult.getDoctorWxopenid());

            WeixinResult weixinResult1 = WeixinApiUtil.sendTemplateMessage(weixinTemplate1,wxapp);

            System.out.println("购买成功模板消息发送结果:"+weixinResult1);
        }


        return res;
    }

    /**
     * 获取患者 or 医生的咨询列表  未完成的
     * @param wxopenid
     * @param identity
     * @param oid
     * @return
     */
    @RequestMapping("get_consult_list")
    public List<VmemberConsult> getConsultList(@RequestParam("wxopenid")String wxopenid,@RequestParam("identity")Integer identity,@RequestParam("oid")Integer oid){

        List<VmemberConsult> vmemberConsults = null;

        if(identity == 2){
           //患者 查询咨询订单__状态_医生信息_医生_最后一条消息_回复时间 or 接单时间
           vmemberConsults = vwxappService.getPatientConsultList(wxopenid,oid);
        }else if(identity == 3){
            vmemberConsults = vwxappService.getDoctorConsultList(wxopenid);
        }

        return vmemberConsults;
    }

    /**
     * 获取患者 or 医生的咨询列表  完成的
     * @param wxopenid
     * @param identity
     * @param oid
     * @return
     */
    @RequestMapping("get_consult_list_completed")
    public List<VmemberConsult> getConsultCompleteList(@RequestParam("wxopenid")String wxopenid,@RequestParam("identity")Integer identity,@RequestParam("oid")Integer oid){

        List<VmemberConsult> vmemberConsults = null;

        if(identity == 2){
            //患者 查询咨询订单__状态_医生信息_医生_最后一条消息_回复时间 or 接单时间
            vmemberConsults = vwxappService.getPatientConsultCompletedList(wxopenid,oid);
        }else  if(identity == 3){
            vmemberConsults = vwxappService.getDoctorConsultCompleteList(wxopenid);
        }

        return vmemberConsults;
    }

    /**
     * 获取患者预约列表  新患者
     * @return
     */
    @RequestMapping("get_consult_list_new")
    public List<VmemberConsult> getConsultNewList(){

        List<VmemberConsult> vmemberConsults = null;

        vmemberConsults = vwxappService.getConsultNewList();


        return vmemberConsults;
    }


    /**
     * 通过微信openid获取医生的基本信息
     * @param wxopenid
     * @return
     */
    @RequestMapping("get_doctor_base_info")
    public WxappMember getDoctorBaseInfo(@RequestParam("wxopenid")String wxopenid){

        return vwxappService.getDoctorBaseInfoByWxopenid(wxopenid);
    }

    /**
     * 申请成为医生
     * @return
     */
    @RequestMapping("apply_become_doctor")
    public int applyBecomeDoctor(WxappMember wxappMember){

        try{
             vwxappService.applyBecomeDoctor(wxappMember);
            return 0;

        }catch (Exception e){
            e.printStackTrace();
            return 1;
        }

    }

    /**
     * 将用户身份 设置为医生
     * @param wxopenid
     */
    @RequestMapping("set_member_identity_with_doctor")
    public void  setMemberIdentityWithDoctor(@RequestParam("wxopenid")String wxopenid){
        vwxappService.setMemberIdentityWithDoctor(wxopenid);
    }


    /**
     * 获取医生余额统计信息
     * @param wxopenid
     */
    @RequestMapping("get_doctor_balance_detail")
    public VdoctorBalance  getDoctorBalanceDetail(@RequestParam("wxopenid")String wxopenid){
        VdoctorBalance vdoctorBalance =vwxappService.getDoctorBalanceDetail(wxopenid);

        return vdoctorBalance==null?new VdoctorBalance():vdoctorBalance;
    }


    /**
     * 医生接单  通知患者
     * @param wxopenid 医生 wxopenid
     * @param id  咨询订单id
     *  @param oid  获取项目信息 appid secret
     * @return
     */
    @RequestMapping("doctor_receipt_consult")
    public String  doctorReceiptConsult(@RequestParam("wxopenid")String wxopenid,@RequestParam("consult_id")Integer id,@RequestParam("oid")Integer oid){
        String res="";
       try{
           vwxappService.doctorReceiptConsult(wxopenid,id);
           res = "0";
           //发送通知给患者
           //数据库获取模板id_管理者id_url   BY 项目id和模板id(自己制定不可变 为1)
           WeixinTemplate weixinTemplate = weixinService.getBuySuccessTemplateById("quanvjk_consult_inform_accept");
           if(weixinTemplate == null){
               System.out.println( "订单生成成功，但消息通知失败，原因:服务器模板ID不存在，请联系客服检查是否配置错误！咨询Id:"+id);
           }

           //受理进度_医生信息
           WxappMember wxappMember = vwxappService.getDoctorDetailByWxopenid(wxopenid);
          //获取咨询订单的信息
           VmemberConsult vmemberConsult = vwxappService.getConsultDetailById(id);

           //填充数据
           WeixinTemplateValue t1 = new WeixinTemplateValue("已为您匹配到医生","#000000");
           //进度
           WeixinTemplateValue t2 = new WeixinTemplateValue(wxappMember.getHospital()+"-"+wxappMember.getRealname()+",为您解答疑惑","#777777");
           //受理时间
           WeixinTemplateValue t3= new WeixinTemplateValue(CalculateUtil.getCurrentDate("yyyy-MM-dd aa hh:mm"),"#777777");
          //受理事项
           WeixinTemplateValue t4= new WeixinTemplateValue("["+vmemberConsult.getLabel()+"]=>["+vmemberConsult.getTitle()+"]","#777777");
           //提交时间
           WeixinTemplateValue t5= new WeixinTemplateValue(vmemberConsult.getCreateTime(),"#777777");
           //标注
           WeixinTemplateValue t6= new WeixinTemplateValue("线上咨询、问诊就上全V健康!","#ff0000");


           //生成data_每个模板的参数都不一样，自动化转换暂时没想到好的方式这里先手动拼接
           StringBuffer str = new StringBuffer();
           str.append("{");
           str.append("\"first\":"+t1.toJson()+",");
           str.append("\"keyword1\":"+t2.toJson()+",");
           str.append("\"keyword2\":"+t3.toJson()+",");
           str.append("\"keyword3\":"+t4.toJson()+",");
           str.append("\"keyword4\":"+t5.toJson()+",");
           str.append("\"remark\":"+t6.toJson());
           str.append("}");

           weixinTemplate.setData(str.toString());

           Wxapp wxapp = wxappService.getWxappInfoByObId(oid);
           //发送消息
           weixinTemplate.setTouser(vmemberConsult.getPatientWxopenid());
           WeixinResult weixinResult = WeixinApiUtil.sendTemplateMessage(weixinTemplate,wxapp);

           //发送失败 提示...
           if(weixinResult.getErrcode() != 0){
               System.out.println("接单成功，但消息通知失败，原因:"+weixinResult.getErrmsg()+"，请联系客服检查是否配置错误！咨询Id:"+id);
           }

       }catch (Exception e){
           e.printStackTrace();
           return e.getMessage();
       }

        return res;
    }


    /**
     * 加载消息....
     * @param id
     * @param  identity 用户设置 当前身份消息已读
     * @return
     */
    @RequestMapping("get_consult_detail_by_id")
    public VmemberConsult getConsultDetailById(@RequestParam("id")Integer id,@RequestParam("identity")int identity){
        try{
            //设置消息已读
            vwxappService.setConsultMessageReadedById(id,identity);

            return vwxappService.getConsultDetailById(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 发送消息....
     */
    @RequestMapping("send_chat_message")
    public int sendChatMessage(VmemberConsultLog vmemberConsultLog,VmemberConsult vmemberConsult,@RequestParam("oid")Integer oid){

        try{

            vwxappService.sendChatMessage(vmemberConsultLog);

            String templateNmae = "";
            String str1="";
            String str2="";
            String str3=vmemberConsultLog.getCtype()==1?vmemberConsultLog.getMessage():"媒体消息";
            String str4="线上咨询、问诊就上全V健康!";

            if(vmemberConsultLog.getIdentity() == 2){
               //发送给医生的
                templateNmae = "quanvjk_consult_inform_message";
                str1 = "你好，你有一条用户咨询待解决";
                str2 = vmemberConsult.getPatientRealname();
           }else{
                //发给医生的
                templateNmae = "quanvjk_consult_inform_reply";
                str1 = "您好，您咨询问题已回复";
                str2 = vmemberConsult.getDoctorHospital()+"-"+vmemberConsult.getDoctorRealname();
            }
            //发送模板消息
            WeixinTemplate weixinTemplate = weixinService.getBuySuccessTemplateById(templateNmae);


            WeixinTemplateValue t1 = new WeixinTemplateValue(str1,"#000000");
            WeixinTemplateValue t2 = new WeixinTemplateValue(str2,"#777777");
            WeixinTemplateValue t3= new WeixinTemplateValue(str3,"#777777");
            WeixinTemplateValue t4= new WeixinTemplateValue(str4,"#ff0000");

            //生成data_每个模板的参数都不一样，自动化转换暂时没想到好的方式这里先手动拼接
            StringBuffer str = new StringBuffer();
            str.append("{");
            str.append("\"first\":"+t1.toJson()+",");
            str.append("\"keyword1\":"+t2.toJson()+",");
            str.append("\"keyword2\":"+t3.toJson()+",");
            str.append("\"remark\":"+t4.toJson());
            str.append("}");

            weixinTemplate.setData(str.toString());

            Wxapp wxapp = wxappService.getWxappInfoByObId(oid);
            //发送消息
            if(vmemberConsultLog.getIdentity() == 2){
                weixinTemplate.setTouser(vmemberConsult.getDoctorWxopenid());
            }else{
                weixinTemplate.setTouser(vmemberConsult.getPatientWxopenid());
            }
            WeixinResult weixinResult = WeixinApiUtil.sendTemplateMessage(weixinTemplate,wxapp);

            //发送失败 提示...
            if(weixinResult.getErrcode() != 0){
                System.out.println("接单成功，但消息通知失败，原因:"+weixinResult.getErrmsg()+"，请联系客服检查是否配置错误！");
            }



            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }


    /**
     * 咨询评价
     * vmemberConsult   id assess 接受 可自动 get 转码 base64编码
     * @return
     */
    @RequestMapping("set_consult_assess")
    public int setConsultAssess(VmemberConsult vmemberConsult){
        try{
            vwxappService.setConsultAssess(vmemberConsult);
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * 获取医生主页信息
     * @param wxopenid 微信公众号openid
     * @return
     */
    @RequestMapping("get_doctor_home_by_wxopenid")
    public Vdoctor getDoctorHomeByWxopenid(@RequestParam("wxopenid")String wxopenid){


        return vwxappService.getDoctorHomeByWxopenid(wxopenid);
    }

    /**
     * 获取小程序的二维码
     *@param oid 项目id 用户获取 appid secret
     */
    @RequestMapping("create_doctor_qrcode")
    public String getWxappQrcode(@RequestParam("wxopenid")String wxopenid,@RequestParam("oid")Integer oid){
        Wxapp wxapp = wxappService.getWxappInfoByObId(oid);

        String path = "/pages/doctor/doctor?openid="+wxopenid;

        String paramstr = "{\"path\":\""+path+"\"}";//一切默认

        InputStream inputStream = WxappApiUtil.getwxacode(wxapp.getAppid(),wxapp.getSecret(),paramstr);

        //图片上传
        MultipartFile multipartFile = null;
        try {
            multipartFile = new MockMultipartFile("qrcode.jpg","qrcode.jpg","",inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String pathstr = "wxapp/v/qrcode";
        String addr = AliyunOss.upload_picture(pathstr,multipartFile);

        System.out.println(addr);
        //更新数据库_不需要更新 保留在自己的客户端
         return addr;
    }

    /**
     * 获取我的医生
     * @param wxopenid 我的openid
     * @return
     */
    @RequestMapping("userInfoAndMydoctor")
    public List<WxappMember> userInfoAndMydoctor(@RequestParam("wxopenid")String wxopenid){
        return vwxappService.userInfoAndMydoctor(wxopenid);
    }


    /**
     * 获取医生的咨询套餐
     * @param wxopenid
     * @return
     */
    @RequestMapping("get_doctor_consult_meal")
    public List<VdoctorMeal> getDoctorConsultMeal(@RequestParam("wxopenid")String wxopenid){
        return vwxappService.getDoctorConsultMeal(wxopenid);
    }
    /**
     * 删除医生的咨询套餐
     * @param wxopenid
     * @return
     */
    @RequestMapping("delete_doctor_consult_meal")
    public void deleteDoctorConsultMeal(@RequestParam("wxopenid")String wxopenid){
         vwxappService.deleteDoctorConsultMeal(wxopenid);
    }

    /**
     * 更改会插入医生的咨询套餐
     * @param vdoctorMeal 套餐信息
     * @return
     */
    @RequestMapping("update_doctor_consult_meal")
    public String updateDoctorConsultMeal(VdoctorMeal vdoctorMeal){

        try{
            vwxappService.updateDoctorConsultMeal(vdoctorMeal);
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }

        return "0";
    }


}
