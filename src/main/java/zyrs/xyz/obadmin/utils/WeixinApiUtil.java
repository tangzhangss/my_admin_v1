package zyrs.xyz.obadmin.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import zyrs.xyz.obadmin.bean.WeixinResult;
import zyrs.xyz.obadmin.bean.WxappResult;

import java.io.IOException;

/**
 * Created by Administrator on 2019/3/1.
 */
public class WeixinApiUtil {
    /**
     * 获取微信的 全局唯一后台接口调用凭据（access_token）
     * @param appid appid
     * @param secret  secret
     * @return
     */
    public static WeixinResult getAccessToken(String appid, String secret,String code){

        ObjectMapper mapper = new ObjectMapper();

        String res = HttpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token","appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code");

        try {
            WeixinResult weixinResult = mapper.readValue(res,WeixinResult.class);

            return weixinResult;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;//服务器错误
    }
}
