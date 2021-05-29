package cn.zy2018.myadmin.controller;

import cn.zy2018.myadmin.utils.CommonUtil;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * Created by Administrator on 2019/2/14.
 */

@Controller
public class AssistController {

    /**
     * 页面  th:img 会导致 layui发送一个请求  原因暂不知道  有的没有极有可能是图片加载失败的回调 赶进度 先不管
     */
    @ResponseBody
    @RequestMapping("/**/undefined")
    public void undefined(){}

    /**
     * 页面  th:img 会导致 layui发送一个请求  原因暂不知道  有的没有极有可能是图片加载失败的回调 赶进度 先不管
     */
    @ResponseBody
    @RequestMapping("/**/null")
    public void assistnull(){}

    /**
     * 暂时不同 springboot 拦截静态资源的东西  现在先作为请求处理  验证文件
     * @param request
     * @return
     */
    @RequestMapping("/MP_verify_*.txt")
    public String txt(HttpServletRequest request){
        //返回文件内容
        return "/txt"+request.getServletPath();
    }

    /**
     * 暂时不同 springboot 拦截静态资源的东西  现在先作为请求处理  验证文件
     * @param request
     * @return
     */
    @RequestMapping("/*.txt")
    @ResponseBody
    public String txt2(HttpServletRequest request){
        return "anon";
    }
    @RequestMapping("/**/*.txt")
    @ResponseBody
    public String txt1(HttpServletRequest request){
        return "anon";
    }


    /**
     * 路由...
     * @param data param json格式
     * @param url 路由地址
     * @return
     */
    @RequestMapping("/tass-route")
    public String route(@RequestParam("url")String url,@RequestParam("data")String data, Map<String,Object> map) throws UnsupportedEncodingException {
        data = URLDecoder.decode(data,"utf-8");
        map.put("data",data);
        CommonUtil.printLog("路由地址:",url);
        return url;
    }

    /**
     * 测试  纯html
     */
    @RequestMapping("/test/{path}")
    public String test(@PathVariable("path")String path){
        return "/test/"+path;
    }
}
