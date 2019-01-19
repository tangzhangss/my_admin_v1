package zyrs.xyz.obadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import zyrs.xyz.obadmin.bean.Ob;
import zyrs.xyz.obadmin.bean.User;
import zyrs.xyz.obadmin.bean.Wxapp;
import zyrs.xyz.obadmin.bean.WxappResult;
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
        return "wxapp/banner";
    }

    /**
     * 跳转商户平台页面....
     */
    @RequestMapping("merchant")
    public String merchant(Map<String,Object> map){
        return "wxapp/merchant";
    }
}
