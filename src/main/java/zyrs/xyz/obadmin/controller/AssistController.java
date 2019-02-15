package zyrs.xyz.obadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
