package zyrs.xyz.obadmin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 管理员 业务控制器_路由
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    @RequestMapping("/menu_poject_relation")
    public String  menu_poject_relation(){

         return "menu_poject_relation";

    }

    @RequestMapping("/menu_set")
    public String  menu_set(){

        return "menu_set";

    }

}
