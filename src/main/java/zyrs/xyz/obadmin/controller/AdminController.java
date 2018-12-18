package zyrs.xyz.obadmin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zyrs.xyz.obadmin.bean.Menu;
import zyrs.xyz.obadmin.bean.MenuSecond;

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

    @ResponseBody
    @RequestMapping(value="/add_or_update_menu",method = RequestMethod.POST)
    public Integer add_or_update_menu(Menu menu){
        try{
            System.out.println(menu);
        }catch (Exception e){
            return 2;//失败
        }

        return 1;//成功
    }

    @ResponseBody
    @RequestMapping(value = "/add_or_update_menu_second",method = RequestMethod.POST)
    public Integer add_or_update_menu_second(MenuSecond menuSecond){
        try{
            System.out.println(menuSecond);
        }catch (Exception e){
            return 2;//失败
        }

        return 1;//成功
    }

}
