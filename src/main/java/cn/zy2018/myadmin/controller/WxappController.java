package cn.zy2018.myadmin.controller;

import cn.zy2018.myadmin.entity.*;
import cn.zy2018.myadmin.data.Result;
import cn.zy2018.myadmin.data.ResultCode;
import cn.zy2018.myadmin.service.ObService;
import cn.zy2018.myadmin.service.WxappService;
import cn.zy2018.myadmin.utils.WxappApiUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@SessionAttributes({"current_user","menuList","statistics"})
@Controller
@RequestMapping("/wxapp")
public class WxappController {

    @Autowired
    private WxappService wxappService;
    @Autowired
    private ObService obService;

    @RequestMapping("baseinfo")
     public String baseinfo(Map<String,Object> map){
        User user = (User)map.get("current_user");
        //获取项目的基本信息_所有
        Ob ob =  obService.getObInfoById(user.getObId());
        //获取项目的appid,secret,
        Wxapp wxapp = wxappService.getWxappInfoByObId(user.getObId());

        map.put("ob",ob);
        map.put("wxapp",wxapp);

        return "wxapp/baseinfo";
    }

    //跳转统计页面_微信接口太慢了改用vue.js
    @RequestMapping("statistics")
    public String statistics(Map<String,Object> map, @RequestParam(value = "month",defaultValue = "0")int month){
        return "wxapp/statistics";
    }

    /**
     * 渲染  所有用户信息
     * like 模糊查询
     * pageNo
     * @return  渲染页面路径
     */
    @RequestMapping("alluser")
    public  String renderAllUser(Map<String,Object> map, @RequestParam(value="like",defaultValue = "")String like, @RequestParam(value="pageNo",defaultValue = "1")Integer pageNo){
        User user = (User) map.get("current_user");

        PageHelper.startPage(pageNo, 10);
        //获取所有的用户信息
        List<WxappMember> wxappMembers = wxappService.getMemberUserInfoByLikeAndOid(like,user.getObId());

        PageInfo<?> appsPageInfo = new PageInfo<>(wxappMembers);

        map.put("members",wxappMembers);

        map.put("pageinfo",appsPageInfo);

        return "/wxapp/userinfo";
    }

    /**
     * 小程序用户 数据统计
     */
    @ResponseBody
    @RequestMapping("statistics_user_info")
    public List<Object> statisticsUserInfo(Map<String,Object> map){
        User user = (User)map.get("current_user");

        //获取当前小程序的appid和secret
        Wxapp wxapp = wxappService.getWxappInfoByObId(user.getObId());

        //获取30天内的用户数据概括
        List<WxappResult> wxappStatisticList =  WxappApiUtil.getWeanalysisAppidDailySummaryTrend(wxapp.getAppid(),wxapp.getSecret());

        List<Object> res = new ArrayList<>();

        res.add(wxappStatisticList);

        //获取昨天的用户
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        String date =  new SimpleDateFormat( "yyyyMMdd" ).format(calendar.getTime());
        WxappResult wxappStatisticDaily = WxappApiUtil.getWeanlysisAppidDailyVisitTrend(wxapp.getAppid(),wxapp.getSecret(),date);
        res.add(wxappStatisticDaily);

        return res;
    }


    /**
     * 跳转轮播图页面....
     */
    @RequestMapping("banner")
    public String banner(Map<String,Object> map){
        User user = (User)map.get("current_user");

        //查询banner对象_项目
        List<Banner> bannerList = wxappService.getWxappBannerByOid(user.getObId());

        map.put("bannerList",bannerList);

        return "wxapp/banner";
    }

    /**
     * 跳转商户平台页面....
     */
    @RequestMapping("merchant")
    public String merchant(Map<String,Object> map){
        User user = (User)map.get("current_user");

        WxappMerchant wxappMerchant = wxappService.getWxappMerchantInfo(user.getObId());
        map.put("merchant",wxappMerchant);
        return "wxapp/merchant";
    }


    /**
     * 新增轮播图模块
     * @param banner  对象信息
     * @return
     */
    @ResponseBody
    @RequestMapping("new_banner_modal")
    public String addNewBannerModal(Banner banner){

        try{
            wxappService.addNewBannerModal(banner);
            return "操作成功！";
        }catch (Exception e){
            return e.getMessage();
        }

    }

    /**
     * 在轮播图下新增图片
     * @return
     */
    @ResponseBody
    @RequestMapping("new_banner_child")
    public String addNewBannerChild(BannerChild bannerChild){

        try{
            wxappService.addNewBannerChild(bannerChild);
            return "操作成功！";
        }catch (Exception e){
            return e.getMessage();
        }

    }
    /**
     * 修改轮播图模块信息
     * @return
     */
    @ResponseBody
    @RequestMapping("modify_banner_modal")
    public String modifyBannerModal(@RequestParam("val")String value,@RequestParam("type")String type,@RequestParam("id")Integer id){
        try{
            wxappService.modifyBannerModal(id,type,value);
            return "操作成功！";
        }catch (Exception e){
            return e.getMessage();
        }

    }

    /**
     * 删除轮播图模块
     * @param id  banner id
     * @return
     */
    @ResponseBody
    @RequestMapping("delete_banner_modal")
    public String deleteBannerModal(@RequestParam("id")Integer id){
        try{
            wxappService.deleteBannerModal(id);
            return "删除成功！";
        }catch (Exception e){
            return e.getMessage();
        }

    }
    /**
     * 删除轮播图模块 中的图片
     * @param id  banner id
     * @return
     */
    @ResponseBody
    @RequestMapping("del_banner_child")
    public String deleteBannerChild(@RequestParam("id")Integer id){
        try{
            wxappService.deleteBannerChild(id);
            return "删除成功！";
        }catch (Exception e){
            return e.getMessage();
        }

    }

    /**
     * 商户平台更新信息
     * @param wxappMerchant
     * @return
     */
    @ResponseBody
    @RequestMapping("update_merchant")
    public String updateMerchant(WxappMerchant wxappMerchant){
        try{
            wxappService.updateMerchant(wxappMerchant);
            return "操作成功！";
        }catch (Exception e){
            return e.getMessage();
        }

    }

    /**
     * 模板消息 设置
     * @return
     */
    @RequestMapping("template")
    public String updateMerchant(Map<String,Object> map){
        //获取当前项目的模板消息
        User user = (User)map.get("current_user");

        List<WeixinTemplate> weixinTemplates = wxappService.getWeixinTemplate(user.getObId());

        map.put("weixinTemplates",weixinTemplates);

        return "wxapp/template";
    }

    /**
     * 插入更新模板消息
     * @return
     */
    @ResponseBody
    @RequestMapping("insert_or_update_template")
    public String insertOrUpdateTemplate(WeixinTemplate template,Map<String,Object> map){
        User user = (User)map.get("current_user");
        template.setOid(user.getObId());
        try{
            wxappService.insertOrUpdateTemplate(template);
            return "操作成功！";
        }catch (Exception e){
            return e.getMessage();
        }

    }

    /**
     * 删除模板消息
     * @return
     */
    @ResponseBody
    @RequestMapping("delete_template")
    public String deleteTemplate(@RequestParam("id")String id){

        try{
            wxappService.deleteTemplate(id);
            return "操作成功！";
        }catch (Exception e){
            return e.getMessage();
        }

    }

    /**
     * 用户充值记录
     * @return
     */
    @RequestMapping("member_recharge")
    public String patientRecharge(Map<String,Object> map,@RequestParam(value="like",defaultValue = "")String like ,@RequestParam(value="pageNo",defaultValue = "1")Integer pageNo){

        User user = (User) map.get("current_user");

        PageHelper.startPage(pageNo, 10);
        //获取所有的用户信息
        List<WeixinOrder> weixinOrders = wxappService.getUserOrder(like,user.getObId());

        PageInfo<?> appsPageInfo = new PageInfo<>(weixinOrders);

        //一对多的时候 总数为错误  会算上所有条数
        map.put("weixinOrders",weixinOrders);

        map.put("pageinfo",appsPageInfo);

        return "/wxapp/member_recharge";
    }


    @GetMapping("aliyun")
    public String aliyunConf(Map<String,Object> map){
        User user = (User) map.get("current_user");

        Aliyun aliyun = wxappService.getAliyunConfByObid(user.getObId());

        map.put("aliyun",aliyun);

        return  "/wxapp/aliyun";
    }
    @PostMapping("aliyun")
    @ResponseBody
    public Result addAliyunConf(Map<String,Object> map){
        User user = (User) map.get("current_user");

        try{

            wxappService.addAliyunConf(user.getObId());

            return  new Result(ResultCode.SUCCESS,user.getObId());

        }catch (Exception e){
            return  new Result(e);
        }

    }
    @PutMapping("aliyun/oss")
    @ResponseBody
    public Result modifyAliyunConfOss(Aliyun aliyun){
        try{
            wxappService.modifyAliyunConfOss(aliyun);

            return  new Result(ResultCode.SUCCESS,null);

        }catch (Exception e){
            return  new Result(e);
        }

    }
}
