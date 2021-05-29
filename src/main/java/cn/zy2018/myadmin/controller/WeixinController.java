package cn.zy2018.myadmin.controller;

import cn.zy2018.myadmin.entity.WeixinResult;
import cn.zy2018.myadmin.entity.Wxapp;
import cn.zy2018.myadmin.entity.WxappMember;
import cn.zy2018.myadmin.service.WxappService;
import cn.zy2018.myadmin.service.api.RedisService;
import cn.zy2018.myadmin.utils.WeixinApiUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

/**
 * Created by Administrator on 2019/3/1.
 */
@Controller
@SessionAttributes({"userInfo"})
@RequestMapping("/weixin")
public class WeixinController {
    @Autowired
    private WxappService wxappService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private WeixinApiUtil weixinApiUtil;

    /**
     * 微信网页授权获取 openid 主要用于web端
     * @param map
     * @param code  code
     * @param param oid@url@param  项目id_授权回调（重定向）页面
     * @return
     */
    @RequestMapping("webauth")
    public String webauth(Map<String,Object> map,@RequestParam("code") String code,@RequestParam("param")String param){

        String [] arr = param.split("@");
        String url = arr[1];
        int oid = Integer.valueOf(arr[0]);
        String urldata="";
        if(arr.length>2){
            urldata = arr[2];
            urldata = urldata.replace("_","&");//转换连接符
            if(StringUtils.isNotBlank(urldata)){//如果不是空串 不能在后面加上？ 不然会导致地址不一样 微信网页开发sdk失效
                urldata = "?"+urldata;
            }
        }
        try{
            Wxapp wxapp = wxappService.getWxappInfoByObId(oid);//全V健康公众号小程序项目 1 不可变
            //获取access_token openid 等信息
            WeixinResult baseinfo =  weixinApiUtil.getAccessToken(wxapp.getAppid(),wxapp.getSecret(),code);

            WxappMember wxappMember = null;
            //验证redis缓存中是否存在数据，存在就表示在其他服务器已经登录
            if(redisService.exists(baseinfo.getOpenid())){
                wxappMember = (WxappMember) redisService.get(baseinfo.getOpenid());

            }else{

                //拉取用户信息
                WeixinResult weixinResult = WeixinApiUtil.getUserInfo(baseinfo.getAccess_token(),baseinfo.getOpenid());

                if (weixinResult.getErrcode() == 0){
                    wxappMember = new WxappMember();
                    wxappMember.setNickname(weixinResult.getNickname());
                    wxappMember.setOpenid(weixinResult.getOpenid());
                    wxappMember.setProvince(weixinResult.getProvince());
                    wxappMember.setAvatars(weixinResult.getHeadimgurl());
                    wxappMember.setCity(weixinResult.getCity());
                    wxappMember.setOid(oid);
                    wxappMember.setUnionid(weixinResult.getUnionid());
                    wxappMember.setGender(weixinResult.getSex());
                    //后台插入更新获取id等相关参数    不返回也可  因为wxappMember引用入参
                    wxappMember = wxappService.insertOrUpdateMemberAndReturnData(wxappMember);
                }else{
                    return "redirect:"+weixinResult.getErrmsg();
                }
            }

            map.put("userInfo",wxappMember);
            redisService.set(wxappMember.getOpenid(),wxappMember,1296000l);//15天
            return "redirect:"+url+urldata;
        }catch (Exception e){
            e.printStackTrace();
            map.put("status","2");//授权失败

            //授权失败的页面
            return "wx/auth";
        }

    }

    /**
     * 微信网页授权获取 openid  主要用于只需要获取微信openid的小程序端
     * @param map
     * @param code  code
     * @param param   项目id 获取微信appid srcret _ 用户ID
     * @return
     */
    @RequestMapping("auth")
    public String auth(Map<String,Object> map,@RequestParam("code") String code,@RequestParam("param")String param){

        String [] ids = param.split("_");

        try{
            Wxapp wxapp = wxappService.getWxappInfoByObId(Integer.valueOf(ids[0]));
            //获取access_token
            WeixinResult weixinResult =  weixinApiUtil.getAccessToken(wxapp.getGzappid(),wxapp.getGzsecret(),code);

            wxappService.updateWeiXinOpenid(weixinResult.getOpenid(),Integer.valueOf(ids[1]));

            map.put("status","0");//授权成功

        }catch (Exception e){
            e.printStackTrace();
            map.put("status","2");//授权失败
        }

        //****************************************
        //授权成功的页面
        return "wx/auth";
    }



}
