package cn.zy2018.myadmin.controller;

import cn.zy2018.myadmin.entity.User;
import cn.zy2018.myadmin.service.UserService;
import cn.zy2018.myadmin.data.Result;
import cn.zy2018.myadmin.data.ResultCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("")
    public String index(){
        return "redirect:index";
    }

    //处理异常
    //如果是双reaml验证则，仅会抛出org.apache.shiro.authc.AuthenticationException这个异常
    //，，所以不能判断账号和密码哪里出了问题>>>待优化
    @ResponseBody
    @ExceptionHandler({AuthenticationException.class})
    public Result authenticationException(Exception ex) throws IOException {


        if(ex.getMessage().equals(IncorrectCredentialsException.class.getName())){
            return new Result(ResultCode.USER_PASSWORD_ERROR,null);
        }else if(ex.getMessage().equals(UnknownAccountException.class.getName())){
            return new Result(ResultCode.USER_NOT_EXISTS,null);
        }
        return new Result(ResultCode.USER_LOGIN_ERROR,null);
    }

    @RequestMapping(value = "user_login",method = RequestMethod.POST)
    @ResponseBody
    public Integer user_login(User user){

        Subject currentUser = SecurityUtils.getSubject();

        if(!currentUser.isAuthenticated()){
            //把用户名和密码封装为usernamePasswordToken
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());

            //将on放在前面 如何 后面为null不会抛出异常
            if("on".equals(user.getRememberMe())){
                token.setRememberMe(true);
            }
            //记住我-token.setRememberMe(true)
            //执行登录
            try{
                currentUser.login(token);

                System.out.println(user.getUsername()+"_登录成功_");

                //获取用户的id  返回0 就是管理员
                User tuser = userService.getUserByUserName(user.getUsername());
                if(tuser.getLevel() == 1){
                    return 0;
                }else{
                    return tuser.getId();
                }
            }catch(AuthenticationException ae){
                throw new AuthenticationException(ae.getClass().getName());
            }
        }
        return 0;
    }


}
