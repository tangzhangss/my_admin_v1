package zyrs.xyz.obadmin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import zyrs.xyz.obadmin.bean.Menu;
import zyrs.xyz.obadmin.bean.Ob;
import zyrs.xyz.obadmin.bean.User;
import zyrs.xyz.obadmin.service.ObService;
import zyrs.xyz.obadmin.service.UserService;

import java.util.List;
import java.util.Map;

@SessionAttributes("currennt_user")
@Controller
@RequestMapping("index")
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObService obService;

    /**
     * 必须要本controller内部....的请求执行前才会起效
     * 在每次数据交互时都需要知道当前用户此cookie信息是否还存在，
     * 为了避免用户长时间不进行操作导致浏览器cookie信息消失，导致后续操作不能得到用户必要的信息
     * 所以需要利用shiro的subject来检测和控制当前用户的状态
     * @param map
     */
    @ModelAttribute
    public void start(Map<String, Object> map){
        //每次请求时 当前用户
        User user = (User) map.get("current_user");

        //如果当前用户cookie信息没有了，查询记住我状态，重新进入
        if(user == null){
            Subject currentUser = SecurityUtils.getSubject();
            //System.out.println("认证："+currentUser.isAuthenticated()+"-记住我："+currentUser.isRemembered());
            if(currentUser.getPrincipal() != null){
                //重新查询用户数据
                User myUser =userService.getUserByUserName((String) currentUser.getPrincipal());
                //保存用户信息
                map.put("current_user", myUser);
            }
        }
    }

    /**
     * 获取首页信息  左侧菜单. .直接请求第一个菜单地址
     * @param map
     * @return
     */
    @RequestMapping("")
    public String index(Map<String,Object> map){
        User user = (User) map.get("current_user");


        //如果是管理员就直接获取管理员菜单...默认菜单
        if(user.getLevel() == 1){

           //获取项目  左侧菜单 包括二级
           List<Menu> menuList =  obService.getMenuWithAdmin();
           System.out.println("菜单+"+menuList+"   用户:"+user);

           map.put("menuList",menuList);

           return "index";
        }

         //根据用户id， 获取项目信息
         List<Ob> obList  = obService.getObInfoByUserId(user.getId());

        //多个项目 其他方式  弹出选择这类的
        if(obList.size()>1){
            return null;
        }
        //用户设置当前logo为项目的logo _更新session信息
        user.setLogo(obList.get(0).getLogo());
        map.put("current_user",user);

        //获取项目  左侧菜单 包括二级
        List<Menu> menuList =  obService.getMenuByObId(obList.get(0).getId());

        System.out.println(menuList);

        return "index";
    }
}
