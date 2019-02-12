package zyrs.xyz.obadmin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import zyrs.xyz.obadmin.bean.*;
import zyrs.xyz.obadmin.service.ObService;
import zyrs.xyz.obadmin.service.VwxappService;
import zyrs.xyz.obadmin.service.WxappService;
import zyrs.xyz.obadmin.utils.HttpRequest;
import zyrs.xyz.obadmin.utils.WxappApiUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@SessionAttributes({"current_user","menuList","statistics"})
@Controller
@RequestMapping("/wxapp_v")
public class VwxappController {

    @Autowired
    private VwxappService vWxappService;

    /**
     * 渲染  所有用户信息
     * like 模糊查询
     * pageNo
     * @return  渲染页面路径
     */
    @RequestMapping("alluser")
    public  String renderAllUser(Map<String,Object> map,@RequestParam(value="like",defaultValue = "")String like,@RequestParam(value="pageNo",defaultValue = "1")Integer pageNo){
        PageHelper.startPage(pageNo, 10);
        //获取所有的用户信息
        List<WxappMember> wxappMembers = vWxappService.getAllUserBaseInfo(like);

        PageInfo<?> appsPageInfo = new PageInfo<>(wxappMembers);

        map.put("members",wxappMembers);
        System.out.println(appsPageInfo);
        map.put("pageinfo",appsPageInfo);

        return "/v/user";
    }
}
