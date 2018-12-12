package zyrs.xyz.obadmin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zyrs.xyz.obadmin.bean.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SessionAttributes("currennt_user")
@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("")
    public String index(){
        return "redirect:/index";
    }

    //处理异常
    //如果是双reaml验证则，仅会抛出org.apache.shiro.authc.AuthenticationException这个异常
    //，，所以不能判断账号和密码哪里出了问题>>>待优化
    @ResponseBody
    @ExceptionHandler({AuthenticationException.class})
    public void authenticationException(Exception ex, HttpServletResponse response) throws IOException {
        System.out.println("异常:"+ex.getMessage());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        if(ex.getMessage().equals(IncorrectCredentialsException.class.getName())){
            response.getWriter().print("密码错误");
            response.getWriter().close();
            return;
        }else if(ex.getMessage().equals(UnknownAccountException.class.getName())){
            response.getWriter().print("用户名不存在");
            response.getWriter().close();
            return;
        }

        response.getWriter().print("登录失败,请检查用户名或密码是否正确");
        response.getWriter().close();
    }

    @RequestMapping(value = "user_login",method = RequestMethod.POST)
    @ResponseBody
    public Integer user_login(User user){
        logger.info("登录用户:"+user);


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

                logger.info(user.getUsername()+"_登录成功_");

            }catch(AuthenticationException ae){
                throw new AuthenticationException(ae.getClass().getName());
            }
        }

        return 1;
    }


}
