package zyrs.xyz.obadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({"current_user","menuList"})
@Controller
@RequestMapping("wxapp")
public class WxappController {

    @RequestMapping("baseinfo")
    public String baseinfo(){
        return "wxapp/baseinfo";
    }
}
