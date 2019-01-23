package zyrs.xyz.obadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import zyrs.xyz.obadmin.bean.*;
import zyrs.xyz.obadmin.service.ObService;
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
        System.out.println(ob+" "+wxapp);
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
     * 小程序用户 数据统计
     */
    @ResponseBody
    @RequestMapping("statistics_user_info")
    public List<Object> statisticsUserInfo(Map<String,Object> map){
        User user = (User)map.get("current_user");

        List<Object> statistics = (List<Object>) map.get("statistics");

        if(statistics == null){
            //获取当前小程序的appid和secret
            Wxapp wxapp = wxappService.getWxappInfoByObId(user.getObId());

            //获取30天内的用户数据概括
            List<WxappResult> wxappStatisticList =  WxappApiUtil.getWeanalysisAppidDailySummaryTrend(wxapp.getAppid(),wxapp.getSecret());

            List<Object> res = new ArrayList<>();

            res.add(wxappStatisticList);

            //获取前天的用户留存
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,-2);
            String date =  new SimpleDateFormat( "yyyyMMdd" ).format(calendar.getTime());
            WxappResult wxappStatisticDaily2 = WxappApiUtil.getWeanlysisAppidDailyRetainInfo(wxapp.getAppid(),wxapp.getSecret(),date);
            res.add(wxappStatisticDaily2);

            map.put("statistics",res);//本次操作一直缓存...不重新发送请求

            return res;
        }



        return statistics;
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


}
