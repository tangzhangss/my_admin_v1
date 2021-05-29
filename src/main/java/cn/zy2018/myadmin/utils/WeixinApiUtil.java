package cn.zy2018.myadmin.utils;

import cn.zy2018.myadmin.entity.WeixinResult;
import cn.zy2018.myadmin.entity.WeixinSdkConfig;
import cn.zy2018.myadmin.entity.WeixinTemplate;
import cn.zy2018.myadmin.entity.WxappMerchant;
import cn.zy2018.myadmin.service.api.RedisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.tika.mime.MimeTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Administrator on 2019/3/1.
 */
@Component
public class WeixinApiUtil {
    @Autowired
    RedisService redisService;

    private static Logger logger = LoggerFactory.getLogger(WeixinApiUtil.class);

    /**
     * 获取微信的 全局唯一后台接口调用凭据（access_token）_网页授权的
     * @param appid appid
     * @param secret  secret
     * @return
     */
    public  WeixinResult getAccessToken(String appid, String secret,String code){

        ObjectMapper mapper = new ObjectMapper();

        String res = HttpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token","appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code");

        try {
            WeixinResult weixinResult = mapper.readValue(res,WeixinResult.class);

            return weixinResult;

        } catch (IOException e) {
            logger.error("【微信网页开发】获取微信公众号网页授权AccessToken失败:"+e.getMessage());
        }

        return null;//服务器错误
    }
    /**
     * 获取微信的 全局唯一后台接口调用凭据（access_token）_普通
     * 和小程序的一样
     * @param appid appid
     * @param secret  secret
     * @return
     */
    public String getBaseAccessToken(String appid, String secret){

        String token = (String) redisService.get("weixinBaseAccessToken_"+appid);
        if(null == token){

            ObjectMapper mapper = new ObjectMapper();

            String res = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token","grant_type=client_credential&appid="+appid+"&secret="+secret);

            try {
                WeixinResult weixinResult = mapper.readValue(res,WeixinResult.class);

                token = weixinResult.getAccess_token();

                //配置错误....
                if(weixinResult.getErrcode()!=0){
                    System.out.println(weixinResult.getErrmsg());
                }else{
                    redisService.set("weixinBaseAccessToken_"+appid,token,Long.valueOf(weixinResult.getExpires_in()-120));
                }

                System.out.println("重新获取base_access_token："+token);

            } catch (Exception e) {

                logger.error("【微信网页开发】获取微信公众号全局唯一后台接口调用凭据accessToken失败:"+e.getMessage());

            }
        }

        return token;
    }

    /**
     *微信统一下单
     * @param wxappMerchant 商户信息
     * @param request  请求参数  必须包含 money openid 属性
     * @param notifyUrl 回调地址
     * @param outTradeNo 商户订单号
     * @return
     */
    public static String unifiedOrder(String appid, WxappMerchant wxappMerchant,String outTradeNo,HttpServletRequest request,String notifyUrl){

        //接受参数(金额)
        String money = request.getParameter("money");
        //接受参数(openid)
        String openid = request.getParameter("openid");

        //接口调用总金额单位为分换算一下(测试金额改成1,单位为分则是0.01,根据自己业务场景判断是转换成float类型还是int类型)
        money = String.valueOf((int)(Float.parseFloat(money)*100));

//        money = "1";

        //创建hashmap(用户获得
        // 签名)
        SortedMap<String, String> paraMap = new TreeMap<String, String>();

        //设置body变量 (支付成功显示在微信支付 商品详情中)
        String body = null;
        body = wxappMerchant.getBody();
        //设置随机字符串
        String nonceStr = RandomStringUtils.randomAlphanumeric(32);

        //设置请求参数(ID)
        paraMap.put("appid",appid);
        //设置请求参数(商户号)
        paraMap.put("mch_id", wxappMerchant.getMchid());
        //设置请求参数(随机字符串)
        paraMap.put("nonce_str", nonceStr);
        //设置请求参数(商品描述)
        paraMap.put("body", body);
        //设置请求参数(商户订单号)
        paraMap.put("out_trade_no", outTradeNo);
        //设置请求参数(总金额)
        paraMap.put("total_fee", money);
        //设置请求参数(终端IP)
        paraMap.put("spbill_create_ip", "127.0.0.1");
        //设置请求参数(通知地址)
        paraMap.put("notify_url",notifyUrl);
        //设置请求参数(交易类型)
        paraMap.put("trade_type", "JSAPI");
        //设置请求参数(openid)(在接口文档中 该参数 是否必填项 但是一定要注意 如果交易类型设置成'JSAPI'则必须传入openid)
        paraMap.put("openid", openid);

        System.out.println("下单参数:"+paraMap.toString());

        //调用逻辑传入参数按照字段名的 ASCII 码从小到大排序（字典序）
         String stringA = WeixinAssistUtil.formatUrlMap(paraMap, false, false);
        //第二步，在stringA最后拼接上key得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值signValue。(签名)
        String sign = DigestUtils.md5Hex(stringA+"&key="+wxappMerchant.getMchkey()).toUpperCase();

        //将参数 编写XML格式
        StringBuffer paramBuffer = new StringBuffer();
        paramBuffer.append("<xml>");
        paramBuffer.append("<appid>"+appid+"</appid>");
        paramBuffer.append("<body>"+body+"</body>");
        paramBuffer.append("<mch_id>"+wxappMerchant.getMchid()+"</mch_id>");
        paramBuffer.append("<nonce_str>"+paraMap.get("nonce_str")+"</nonce_str>");
        paramBuffer.append("<notify_url>"+paraMap.get("notify_url")+"</notify_url>");
        paramBuffer.append("<openid>"+paraMap.get("openid")+"</openid>");
        paramBuffer.append("<out_trade_no>"+paraMap.get("out_trade_no")+"</out_trade_no>");
        paramBuffer.append("<spbill_create_ip>"+paraMap.get("spbill_create_ip")+"</spbill_create_ip>");
        paramBuffer.append("<total_fee>"+paraMap.get("total_fee")+"</total_fee>");
        paramBuffer.append("<trade_type>"+paraMap.get("trade_type")+"</trade_type>");
        paramBuffer.append("<sign>"+sign+"</sign>");
        paramBuffer.append("</xml>");


        String res = HttpRequest.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder",paramBuffer.toString());

        return res;
    }

    /**
     * wx支付接口 生成签名算法
     * @param appid  APPID
     * @param key  商户平台的 key
     * @param prepayId  统一下单接口返回的 prepay_id
     * @return
     */
    public static JSONObject generateSignature(String appid,String key,String prepayId) {
                 //实例化返回对象
                 JSONObject resultJson = new JSONObject();

                 //创建 时间戳
                 String timeStamp = Long.valueOf(System.currentTimeMillis()).toString();
                 //创建 随机串
                 String nonceStr = RandomStringUtils.randomAlphanumeric(32);
                 //创建 MD5
                 String signType = "MD5";

                 //创建hashmap(用户获得签名)
                 SortedMap<String, String> paraMap = new TreeMap<String, String>();
                 //设置(小程序ID)(这块一定要是大写)
                 paraMap.put("appId", appid);
                 //设置(时间戳)
                 paraMap.put("timeStamp", timeStamp);
                 //设置(随机串)
                 paraMap.put("nonceStr", nonceStr);
                 //设置(数据包)
                 paraMap.put("package", "prepay_id="+prepayId);
                 //设置(签名方式)
                 paraMap.put("signType", signType);

                 //调用逻辑传入参数按照字段名的 ASCII 码从小到大排序（字典序）
                 String stringA = WeixinAssistUtil.formatUrlMap(paraMap, false, false);

                 //第二步，在stringA最后拼接上key得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值signValue。(签名)
                 String sign = DigestUtils.md5Hex(stringA+"&key="+key).toUpperCase();

                 if(StringUtils.isNotBlank(sign)){
                         //返回签名信息
                         resultJson.put("sign", sign);
                         //返回随机串(这个随机串是新创建的)
                         resultJson.put("nonceStr", nonceStr);
                         //返回时间戳
                         resultJson.put("timeStamp", timeStamp);
                         //返回数据包
                         resultJson.put("package", "prepay_id="+prepayId);
                 }
                 return resultJson;
   }


    /**
     * 发送 微信公众号模板消息
     * @return  结果
     */
    public  WeixinResult sendTemplateMessage(WeixinTemplate weixinTemplate,String appid,String secret){

        String access_token = getBaseAccessToken(appid,secret);

        StringBuffer str = new StringBuffer();
        str.append("{");
        str.append("\"touser\":\""+weixinTemplate.getTouser()+"\",");
        str.append("\"template_id\":\""+weixinTemplate.getTemplateId()+"\",");
        str.append("\"url\":\""+weixinTemplate.getUrl()+"\",");
        //颜色无效
        //str.append("\"topcolor\":\""+weixinTemplate.getTopcolor()+"\",");
        str.append("\"data\":"+weixinTemplate.getData()+"");
        str.append("}");

        String param = str.toString();
        System.out.println("发送模板消息，数据:"+param);
        try {

            String res = HttpRequest.sendPost("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token,param);

            WeixinResult weixinResult = new ObjectMapper().readValue(res,WeixinResult.class);

            return weixinResult;

        } catch (Exception e) {
            logger.error("【微信网页开发】发送微信公众号模板消息失败:"+e.getMessage());
            return null;
        }
    }

    /**
     * 微信授权的时候获取用户信息
     */
    public static WeixinResult getUserInfo(String access_token, String openid) {
        ObjectMapper mapper = new ObjectMapper();

        String res = HttpRequest.sendGet("https://api.weixin.qq.com/sns/userinfo","access_token="+access_token+"&openid="+openid+"&lang=zh_CN");

        try {
            WeixinResult weixinresult = mapper.readValue(res,WeixinResult.class);

            return weixinresult;

        } catch (IOException e) {
            logger.error("【微信网页开发】获取jsapi_ticket失败:"+e.getMessage());
        }

        return new WeixinResult();
    }

    /**
     * 获取jsapi_ticket
     */
    public WeixinResult getJsapiTicket(String appid,String secret) {

        String access_token = getBaseAccessToken(appid,secret);

        String res = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket","access_token="+access_token+"&type=jsapi");

        ObjectMapper mapper = new ObjectMapper();

        try {
            WeixinResult weixinresult = mapper.readValue(res,WeixinResult.class);

            return weixinresult;

        } catch (IOException e) {
            logger.error("【微信网页开发】获取素材失败:"+e.getMessage());
        }

        return null;
    }

    /**
     * 获取微信js接口调用配置
     */
    public  WeixinSdkConfig getWeixinSdkConfig(String appid ,String secret,String url){
        //1.获取ticket
        String ticket=(String) redisService.get("weixinSdkTicket_"+appid);
        //2.获取 ticket expires_in 等信息
        if(null == ticket){
            WeixinResult weixinResult = getJsapiTicket(appid,secret);

            ticket = weixinResult.getTicket();

            System.out.println("重新获取weixinSdkticket："+ticket);

            if(weixinResult.getErrcode()==0){
                redisService.set("weixinSdkTicket_"+appid,weixinResult.getTicket(),Long.valueOf(weixinResult.getExpires_in()-120));
            }
        }

        //随机字符串
        String nonceStr = RandomStringUtils.randomAlphanumeric(32);
        //时间戳
        String timestamp = Long.valueOf(System.currentTimeMillis()).toString();

        SortedMap<String, String> paraMap = new TreeMap<String, String>();

        paraMap.put("noncestr", nonceStr);
        paraMap.put("jsapi_ticket",ticket);
        paraMap.put("timestamp", timestamp);
        paraMap.put("url", url);

        String stringA = WeixinAssistUtil.formatUrlMap(paraMap, false, false);
        String sign = DigestUtils.sha1Hex(stringA);

        return  new WeixinSdkConfig(appid,timestamp,nonceStr,sign);
    }

    /**
     *获取素材
     * @param appid
     * @param secret
     * @param mediaId
     * @param aliyunOss
     * @return  上传到服务器（阿里云） 返回 地址
     */
    public  String getMedia(String appid ,String secret,String mediaId,AliyunOss aliyunOss){

        String access_token = getBaseAccessToken(appid,secret);
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/media/get?access_token="+access_token+"&media_id="+mediaId;

        String res = "";//图片地址
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.connect();
            InputStream input = conn.getInputStream();

//            System.out.println(conn.getResponseMessage());  OK
            //conn.getContentType()如果没有的话就是text/plain
            String ext  = MimeTypes.getDefaultMimeTypes().forName(conn.getContentType()).getExtension();
//            System.out.print("文件后缀:"+ext);

            res = aliyunOss.uploadFile("weixin_meida",input,ext);

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return res;
    }

    /**
     *用户完整的信息，可用于验证用户是否关注公众号
     * @return
     */
    public WeixinResult getUserInfoTwo(String appid,String secret,String openid){
        String access_token = getBaseAccessToken(appid,secret);

        String res = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/user/info","access_token="+access_token+"&openid="+openid+"&&lang=zh_CN");

        ObjectMapper mapper = new ObjectMapper();

        try {
            WeixinResult weixinresult = mapper.readValue(res,WeixinResult.class);
            return weixinresult;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new WeixinResult();
    }
}
