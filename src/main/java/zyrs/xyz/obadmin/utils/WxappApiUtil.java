package zyrs.xyz.obadmin.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import zyrs.xyz.obadmin.bean.WxappResult;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WxappApiUtil {


    /**
     * 获取小程序的 全局唯一后台接口调用凭据（access_token）
     * @param appid 小程序appid
     * @param secret  小程序secret
     * @return
     */
    public static WxappResult getAccessToken(String appid,String secret){

        ObjectMapper mapper = new ObjectMapper();

        String res = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token","grant_type=client_credential&appid=wx8c45fc78f9bcdb6c&secret=c5bbfc58460d111c8f60d13ebcfe1b72");

        try {
            WxappResult wxappResult = mapper.readValue(res,WxappResult.class);

            return wxappResult;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;//服务器错误
    }

    /**
     * 获取微信小程序  日访问 趋势
     * @param appid 小程序appid
     * @param secret  小程序secret
     * @param date  日期  yyyyMMdd
     * @return
     */
    public static WxappResult getWeanlysisAppidDailyVisitTrend(String appid, String secret,String date){
        String access_token = getAccessToken(appid, secret).getAccess_token();
        String param = "{\"begin_date\":\""+date+"\",\"end_date\":\""+date+"\"}";
        String res = HttpRequest.sendPost("https://api.weixin.qq.com/datacube/getweanalysisappiddailyvisittrend?access_token="+access_token,param);

        try {

            WxappResult wxappResult = new ObjectMapper().readValue(res,WxappResult.class);

            return wxappResult;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 获取微信小程序  日访问留存
     * @param appid 小程序appid
     * @param secret  小程序secret
     * @param date  日期  yyyyMMdd
     * @return
     */
    public static WxappResult getWeanlysisAppidDailyRetainInfo(String appid, String secret,String date){
        String access_token = getAccessToken(appid, secret).getAccess_token();
        String param = "{\"begin_date\":\""+date+"\",\"end_date\":\""+date+"\"}";
        String res = HttpRequest.sendPost("https://api.weixin.qq.com/datacube/getweanalysisappiddailyretaininfo?access_token="+access_token,param);

        try {

            WxappResult wxappResult = new ObjectMapper().readValue(res,WxappResult.class);

            return wxappResult;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 累计30天的小程序 累计用户数  转发次数 转发人数
     * @return
     */
    public static  List<WxappResult> getWeanalysisAppidDailySummaryTrend(String appid, String secret){

        //当月时间段计算
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-31);

        List<WxappResult> wxappResultList = new ArrayList<>();
        String param = "";
        String res = "";
        String access_token = getAccessToken(appid, secret).getAccess_token();
        ObjectMapper mapper = new ObjectMapper();

        for(int i = 30;i>0;i--){
            calendar.add(Calendar.DATE,+1);
            //前30天时间
            String date =  new  SimpleDateFormat( "yyyyMMdd" ).format(calendar.getTime());

            //必须要json字符串
            param = "{\"begin_date\":\""+date+"\",\"end_date\":\""+date+"\"}";

            res = HttpRequest.sendPost("https://api.weixin.qq.com/datacube/getweanalysisappiddailysummarytrend?access_token="+access_token,param);

            try {

                WxappResult wxappResult = mapper.readValue(res,WxappResult.class);

                wxappResultList.add(wxappResult);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }





        return wxappResultList;
    }
}
