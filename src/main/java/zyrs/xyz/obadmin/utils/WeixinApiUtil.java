package zyrs.xyz.obadmin.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import zyrs.xyz.obadmin.bean.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Administrator on 2019/3/1.
 */
public class WeixinApiUtil {
    /**
     * 获取微信的 全局唯一后台接口调用凭据（access_token）_网页授权的
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
    /**
     * 获取微信的 全局唯一后台接口调用凭据（access_token）_普通
     * 和小程序的一样
     * @param appid appid
     * @param secret  secret
     * @return
     */
    public static WeixinResult getBaseAccessToken(String appid, String secret){

        ObjectMapper mapper = new ObjectMapper();

        String res = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token","grant_type=client_credential&appid="+appid+"&secret="+secret);

        try {
            WeixinResult weixinResult = mapper.readValue(res,WeixinResult.class);

            return weixinResult;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;//服务器错误
    }

    /**
     *微信统一下单
     * @param wxapp  小程序信息
     * @param wxappMerchant 商户信息
     * @param request  请求参数
     * @param notifyUrl 回调地址
     * @param outTradeNo 商户订单号
     * @param isWxapp  是否是小程序 可微信公众号调用
     * @return
     */
    public static String unifiedOrder(Wxapp wxapp, WxappMerchant wxappMerchant,String outTradeNo,HttpServletRequest request,String notifyUrl,Boolean isWxapp){

        //接受参数(金额)
        String amount = request.getParameter("amount");
        //接受参数(openid)
        String openid = request.getParameter("openid");


        //接口调用总金额单位为分换算一下(测试金额改成1,单位为分则是0.01,根据自己业务场景判断是转换成float类型还是int类型)
        amount = Integer.valueOf((Integer.parseInt(amount)*100)).toString();
        //String amount = Float.valueOf((Float.parseFloat(amount)*100)).toString();
        //amount =  "1";
        //创建hashmap(用户获得
        // 签名)
        SortedMap<String, String> paraMap = new TreeMap<String, String>();

        //设置body变量 (支付成功显示在微信支付 商品详情中)
        String body = null;
        body = wxappMerchant.getBody();
        //设置随机字符串
        String nonceStr = RandomStringUtils.randomAlphanumeric(32);

        //设置请求参数(ID)
        //paraMap.put("appid", isWxapp?wxapp.getAppid():wxapp.getGzappid());

        paraMap.put("appid", isWxapp?wxapp.getAppid():wxapp.getGzappid());
        //设置请求参数(商户号)
        paraMap.put("mch_id", wxappMerchant.getMchid());
        //设置请求参数(随机字符串)
        paraMap.put("nonce_str", nonceStr);
        //设置请求参数(商品描述)
        paraMap.put("body", body);
        //设置请求参数(商户订单号)
        paraMap.put("out_trade_no", outTradeNo);
        //设置请求参数(总金额)
        paraMap.put("total_fee", amount);
        //设置请求参数(终端IP)
        paraMap.put("spbill_create_ip", "127.0.0.1");
        //设置请求参数(通知地址)
        paraMap.put("notify_url",notifyUrl);
        //设置请求参数(交易类型)
        paraMap.put("trade_type", "JSAPI");
        //设置请求参数(openid)(在接口文档中 该参数 是否必填项 但是一定要注意 如果交易类型设置成'JSAPI'则必须传入openid)
        paraMap.put("openid", openid);

        //调用逻辑传入参数按照字段名的 ASCII 码从小到大排序（字典序）
         String stringA = WeixinPayUtil.formatUrlMap(paraMap, false, false);
        //第二步，在stringA最后拼接上key得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值signValue。(签名)
        String sign = MD5Util.MD5(stringA+"&key="+wxappMerchant.getMchkey()).toUpperCase();

        //将参数 编写XML格式
        StringBuffer paramBuffer = new StringBuffer();
        paramBuffer.append("<xml>");
        paramBuffer.append("<appid>"+(isWxapp?wxapp.getAppid():wxapp.getGzappid())+"</appid>");
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
                 String stringA = WeixinPayUtil.formatUrlMap(paraMap, false, false);

                 //第二步，在stringA最后拼接上key得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值signValue。(签名)
                 String sign = MD5Util.MD5(stringA+"&key="+key).toUpperCase();

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
    public static WeixinResult sendTemplateMessage(WeixinTemplate weixinTemplate,Wxapp wxapp){

        String access_token = getBaseAccessToken(wxapp.getGzappid(),wxapp.getGzsecret()).getAccess_token();

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
            e.printStackTrace();
            return null;
        }
    }
}
