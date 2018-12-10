package zyrs.xyz.obadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("currennt_user")
@Controller
public class InitController {
    /**
     * 在每次数据交互时都需要知道当前用户此cookie信息是否还存在，
     * 为了避免用户长时间不进行操作导致浏览器cookie信息消失，导致后续操作不能得到用户必要的信息
     * 所以需要利用shiro的subject来检测和控制当前用户的状态
     * @param map
     */
//    @ModelAttribute
//    public void start(Map<String, Object> map){
//        //每次请求时 当前用户
//        User user = (User) map.get("current_user");
//
//        //如果当前用户cookie信息没有了，查询记住我状态，重新进入
//        if(user == null){
//            Subject currentUser = SecurityUtils.getSubject();
//            //System.out.println("认证："+currentUser.isAuthenticated()+"-记住我："+currentUser.isRemembered());
//            if(currentUser.getPrincipal() != null){
//                //重新查询用户数据
//                User myUser =userService.getListByUserName((String) currentUser.getPrincipal());
//
//                //保存用户信息
//                map.put("current_user", myUser);
//            }
//        }
//    }
}
