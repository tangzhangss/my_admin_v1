package cn.zy2018.myadmin.controller;

import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import cn.zy2018.myadmin.entity.Menu;
import cn.zy2018.myadmin.entity.Ob;
import cn.zy2018.myadmin.entity.User;
import cn.zy2018.myadmin.service.ObService;
import cn.zy2018.myadmin.service.UserService;

import java.util.List;
import java.util.Map;

@SessionAttributes({"current_user","menuList"})
@Controller
@RequestMapping("/index")
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
            User myUser = null;
            //System.out.println("认证："+currentUser.isAuthenticated()+"-记住我："+currentUser.isRemembered());
            if(null!=currentUser && currentUser.getPrincipal() != null){
                //重新查询用户数据
                 myUser =userService.getUserByUserName((String) currentUser.getPrincipal());
                //保存用户信息
                map.put("current_user", myUser);
            }
        }
    }

    /**
     * 获取首页信息  左侧菜单. .直接请求第一个菜单地址
     * @param  oid 项目id 如果是 客户登录选择项目要或者管理点击客户项目 根据此参数进入客户的管理界面
     * @return
     */
    @RequestMapping("")
    public String index(Map<String,Object> map,@RequestParam(value = "oid",defaultValue = "0")Integer oid){
        User user = (User) map.get("current_user");

        //菜单获取
        List<Menu> menuList = null;

        //管理员
        if(oid == 0 && user.getLevel() == 1){
            //获取项目  左侧菜单 包括二级
            menuList =  obService.getMenuWithAdmin();

            //设置被选中 菜单的id
            user.setSelectedMenuId(menuList.get(0).getMenuSecondList().get(0).getId());
            map.put("menuList",menuList);
            map.put("current_user",user);

           return  "forward:"+menuList.get(0).getMenuSecondList().get(0).getUrl();
        }
        //检查oid 如何oid与当前登录用户不同又不是管理员 就变成自己的oid

        //客户登录时oid也是0_客户 目前没法实现记住我登录，因为项目ID不再user里面_先选后进  非默认...
         //改:加一个字段记录上一次项目id 并赋值给obid
         oid = oid==0?user.getObId():oid;

         if(oid==null){
             return "forward:/login";
         }

         //记录当前项目更换记录_更新最后一次进入项目id
        //20180903更改  这样会导致 登录用户换一个oid参数就可以进入别人的项目
        //之前这样考虑是为了每次登录不必都选择项目，当时客户基本都只有一个项目
        //确保是当前项目_如果是管理员则不需要验证——因为管理员需要这个机制管理客户项目
        if(user.getLevel() != 1 &&oid != user.getObId()){
            //判断此项目是不是改客户所有
            if(null==userService.getObjectByUserIdAndOid(user.getId(),oid) ){
                //这是别人的项目ID  强行换成自己上一次  （自己的项目ID）
                oid = user.getObId();
            }else{
                userService.updatePrevObId(user.getId(),oid);
            }
        }



         //根据项目ID， 获取项目信息
         Ob ob = obService.getObInfoById(oid);

         //用户设置当前logo为项目的logo _项目id_更新session信息
         user.setLogo(ob.getLogo());
         user.setObId(ob.getId());



        //获取菜单
        //根据项目id， 获取项目菜单信息
        menuList =  obService.getMenuByObId(oid);
        //设置被选中 菜单的id
        user.setSelectedMenuId(menuList.get(0).getMenuSecondList().get(0).getId());
        map.put("menuList",menuList);
        map.put("current_user",user);

        return "forward:"+menuList.get(0).getMenuSecondList().get(0).getUrl();
    }

    /**
     * 业务 控制器路由
     */
    @RequestMapping("/route")
    public String route(@RequestParam("control")String control,@RequestParam("url")String url,
                        @RequestParam(value = "menuId",defaultValue = "0")Integer menuId,Map<String,Object> map){
        //设置当前被选中菜单
        if(menuId!=0){
            User user = (User) map.get("current_user");
            user.setSelectedMenuId(menuId);
            map.put("current_user",user);
        }

        return "forward:/"+control+"/"+url;
    }
    /**
     * 业务 控制器路由
     * 直接跳转页面不需要转发控制器
     * data格式=id1:5@id2:6
     */
    @RequestMapping("/route2")
    public String route2(@RequestParam(value = "data",required = false)String data,@RequestParam("url")String url,
                         @RequestParam(value = "menuId",defaultValue = "0")Integer menuId,Map<String,Object> map){
        //设置当前被选中菜单
        if(menuId!=0){
            User user = (User) map.get("current_user");
            user.setSelectedMenuId(menuId);
            map.put("current_user",user);
        }

        if (null != data){
            String [] param=data.split("@");
            JSONObject object = new JSONObject();
            String [] str = null;
            for (int i=0;i<param.length;i++) {
                str = param[i].split(":");
                object.put(str[0],str[1]);
            }

            map.put("data",object);
        }
        return url;
    }
}
