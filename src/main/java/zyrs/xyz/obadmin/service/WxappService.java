package zyrs.xyz.obadmin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zyrs.xyz.obadmin.bean.User;
import zyrs.xyz.obadmin.bean.Wxapp;
import zyrs.xyz.obadmin.bean.WxappResult;
import zyrs.xyz.obadmin.mapper.UserMapper;
import zyrs.xyz.obadmin.mapper.WxappMapper;
import zyrs.xyz.obadmin.utils.HttpRequest;
import zyrs.xyz.obadmin.utils.MD5Util;

import java.io.IOException;
import java.util.List;

@Service
public class WxappService {

    @Autowired
    private WxappMapper wxappMapper;


    public Wxapp getWxappInfoByObId(Integer obId) {
        return wxappMapper.getWxappInfoByObId(obId);
    }

    public void addOrUpdateWxapp(Wxapp wxapp) {
        wxappMapper.addOrUpdateWxapp(wxapp);
    }



    /**
     * 获取小程序的 全局唯一后台接口调用凭据（access_token）
     * @param appid 小程序appid
     * @param secret  小程序secret
     * @return
     */
    public Object getAccessToken(String appid,String secret){

        ObjectMapper mapper = new ObjectMapper();

        String res = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token","grant_type=client_credential&appid=wx8c45fc78f9bcdb6c&secret=c5bbfc58460d111c8f60d13ebcfe1b72");

        try {
            WxappResult wxappResult = mapper.readValue(res,WxappResult.class);

            System.out.println("微信接口返回:"+wxappResult.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;//服务器错误
    }
}
