package zyrs.xyz.obadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import zyrs.xyz.obadmin.bean.User;
import zyrs.xyz.obadmin.bean.WeixinResult;
import zyrs.xyz.obadmin.bean.Wxapp;
import zyrs.xyz.obadmin.service.WxappService;
import zyrs.xyz.obadmin.utils.WeixinApiUtil;

import java.util.Map;

/**
 * Created by Administrator on 2019/3/1.
 */
@Controller
@RequestMapping("/weixin")
public class WeixinController {
    @Autowired
    private WxappService wxappService;

    /**
     * 微信网页授权获取 openid
     * @param map
     * @param code  code
     * @param param   项目id 获取微信appid srcret _ 用户ID
     * @return
     */
    @ResponseBody
    @RequestMapping("auth")
    public void auth(Map<String,Object> map,@RequestParam("code") String code,@RequestParam("param")String param){

        String [] ids = param.split("_");

        Wxapp wxapp = wxappService.getWxappInfoByObId(Integer.valueOf(ids[0]));
        //获取access_token
        WeixinResult weixinResult =  WeixinApiUtil.getAccessToken(wxapp.getGzappid(),wxapp.getGzsecret(),code);
        System.out.println(weixinResult);
        wxappService.updateWeiXinOpenid(weixinResult.getOpenid(),Integer.valueOf(ids[1]));

        //****************************************
        //授权成功的页面
    }
}
