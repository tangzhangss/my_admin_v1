package zyrs.xyz.obadmin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import zyrs.xyz.obadmin.bean.*;
import zyrs.xyz.obadmin.service.ObService;
import zyrs.xyz.obadmin.service.UserService;

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
    @Autowired
    private UserService userService;


    @RequestMapping("/project_manage")
    public String  project_manage(Map<String,Object> map){

        //获取所有项目的信息
        List<Ob> obList = obService.get_all_project_list();
        map.put("obList",obList);

        return "project_manage";
    }

    @RequestMapping("/user_manage")
    public String  user_manage(Map<String,Object> map){

        //获取所有 \客户\ 的信息
        List<User> userList = userService.get_all_user_list();
        map.put("userList",userList);

        return "user_manage";
    }

    @RequestMapping("/menu_poject_relation")
    public String  menu_poject_relation(Map<String,Object>map){

        List<MenuProject> menuProjects = obService.getMenuProjectsList();

        System.out.println("菜单-项目列表:"+menuProjects);

        map.put("menuProjects",menuProjects);

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


    @ResponseBody
    @RequestMapping(value = "/add_or_update_user",method = RequestMethod.POST)
    public String add_or_update_user(User user){
        try{
            System.out.println(user);
            if(userService.addOrUpdateUser(user) == 2){
                return "用户已存在,请点击'修改按钮'操作客户信息！";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误！";//失败
        }
        return "1";//成功
    }
    @ResponseBody
    @RequestMapping(value = "/delete_user")
    public Integer deleteUser(@RequestParam("id")Integer id){
        try{
            userService.deleteUserById(id);
        }catch (Exception e){
            e.printStackTrace();
            return 2;
        }
        return 1;
    }

    @ResponseBody
    @RequestMapping(value = "/add_menu_project",method = RequestMethod.POST)
    public String addMenuProject(@RequestParam("mid")Integer mid,@RequestParam("oid")Integer oid){
        try{

            int temp_res = obService.addMenuProject(mid,oid);

           if(temp_res == 2){
               return "菜单已关联此项目!";
           }

           if(temp_res == 3){
               return "项目不存在!";
           }


        }catch (Exception e){
            e.printStackTrace();
            return "系统错误！";//失败
        }
        return "1";//成功
    }
    @ResponseBody
    @RequestMapping(value = "/del_menu_project")
    public Integer delMenuProject(@RequestParam("id")Integer id){
        try{
            obService.delMenuProject(id);
        }catch (Exception e){
            e.printStackTrace();
            return 2;
        }
        return 1;
    }

    @ResponseBody
    @RequestMapping("get_ob_info_by_userId")
    public List<Ob> getObInfoByUserId(@RequestParam("id")Integer id){
        return obService.getObInfoByUserId(id);
    }
}
