package zyrs.xyz.obadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import zyrs.xyz.obadmin.bean.Ob;
import zyrs.xyz.obadmin.bean.User;
import zyrs.xyz.obadmin.bean.Wxapp;
import zyrs.xyz.obadmin.service.ObService;
import zyrs.xyz.obadmin.service.WxappService;

import java.util.Map;

@SessionAttributes({"current_user","menuList"})
@Controller
@RequestMapping("wxapp")
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
}
