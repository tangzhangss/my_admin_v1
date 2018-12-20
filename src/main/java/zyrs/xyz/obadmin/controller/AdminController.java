package zyrs.xyz.obadmin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import zyrs.xyz.obadmin.bean.Menu;
import zyrs.xyz.obadmin.bean.MenuSecond;
import zyrs.xyz.obadmin.bean.Ob;
import zyrs.xyz.obadmin.service.ObService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 管理员 业务控制器_路由
 */
@SessionAttributes({"current_user","menuList"})
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private ObService obService;


    @RequestMapping("/project_manage")
    public String  project_manage(Map<String,Object> map){

        //获取所有项目的信息
        List<Ob> obList = obService.get_all_project_list();
        map.put("obList",obList);

        return "project_manage";
    }

    @RequestMapping("/menu_poject_relation")
    public String  menu_poject_relation(Map<String,Object>map){

         return "menu_poject_relation";
    }

    @RequestMapping("/menu_set")
    public String  menu_set(Map<String,Object> map){

        map.put("allMenuList",obService.getMenu());

        return "menu_set";
    }

    @ResponseBody
    @RequestMapping(value="/add_or_update_menu",method = RequestMethod.POST)
    public Integer add_or_update_menu(Menu menu, Map<String,Object> map){
        try{
            System.out.println(menu);
            obService.add_or_update_menu(menu);
        }catch (Exception e){
            e.printStackTrace();
            return 2;//失败
        }
        return 1;//成功
    }

    @ResponseBody
    @RequestMapping(value = "/add_or_update_menu_second",method = RequestMethod.POST)
    public Integer add_or_update_menu_second(MenuSecond menuSecond,Map<String,Object> map){
        try{
            System.out.println(menuSecond);
            obService.add_or_update_menu_second(menuSecond);
        }catch (Exception e){
            e.printStackTrace();
            return 2;//失败
        }
        return 1;//成功
    }

    @ResponseBody
    @RequestMapping(value = "/del_menu")
    public void del_menu(@RequestParam("id")Integer id){
        obService.del_menu(id);
    }

    @ResponseBody
    @RequestMapping(value = "/del_menu_second")
    public void del_menu_second(@RequestParam("id")Integer id){
        obService.del_menu_second(id);
    }

    @ResponseBody
    @RequestMapping(value = "/add_or_update_project",method = RequestMethod.POST)
    public Integer add_or_update_project(Ob ob){
        try{
            System.out.println(ob);
            obService.add_or_update_project(ob);
        }catch (Exception e){
            e.printStackTrace();
            return 2;//失败
        }
        return 1;//成功
    }
    @ResponseBody
    @RequestMapping(value = "/delete_project")
    public Integer delete_project(@RequestParam("id")Integer id){
        try{
            obService.delete_project(id);
        }catch (Exception e){
            e.printStackTrace();
            return 2;
        }
        return 1;
    }

    @ResponseBody
    @RequestMapping(value = "/project_relay")
    public void project_relay(@RequestParam("id")Integer id,@RequestParam("count")Integer count){

        obService.project_relay(id,count);
    }

}
