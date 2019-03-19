package zyrs.xyz.obadmin.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import net.sf.json.JSONObject;
import zyrs.xyz.obadmin.bean.WxappMember;
import zyrs.xyz.obadmin.bean.WxappResult;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
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

        String res = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token","grant_type=client_credential&appid="+appid+"&secret="+secret);

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

    public static WxappResult authLogin(String appid, String secret, String code){

        String access_token = getAccessToken(appid, secret).getAccess_token();

        ObjectMapper mapper = new ObjectMapper();

        String res = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session","appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code");

        try {
            WxappResult wxappResult = mapper.readValue(res,WxappResult.class);

            return wxappResult;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *获取小程序的二维码
     * @param appid
     * @param secret
     * @param paramJson 请求参数  JSON 字符串  路径等
     * @return
     */
    public static InputStream getwxacode(String appid, String secret, String paramJson) {

        String access_token = getAccessToken(appid, secret).getAccess_token();

        String res = HttpRequest.sendPost("https://api.weixin.qq.com/wxa/getwxacode?access_token=" + access_token, paramJson);
        try
        {
            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacode?access_token="+access_token);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());

            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            return bis;
        }
        catch (Exception e)
        {
            e.printStackTrace();

            return null;
        }
    }
}
