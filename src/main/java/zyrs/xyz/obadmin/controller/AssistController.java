package zyrs.xyz.obadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/2/14.
 */
@Controller
public class AssistController {

    /**
     * 页面  th:img 会导致 layui发送一个请求  原因暂不知道  有的没有极有可能是图片加载失败的回调 赶进度 先不管
     */
    @ResponseBody
    @RequestMapping("/*/undefined")
    public void undefined(){}

    /**
     * 暂时不同 springboot 拦截静态资源的东西  现在先作为请求处理  验证文件
     * @param request
     * @return
     */
    @RequestMapping("/*.txt")
    public String txt(HttpServletRequest request){
        //返回文件内容
        return "txt/"+request.getServletPath();
    }

}
