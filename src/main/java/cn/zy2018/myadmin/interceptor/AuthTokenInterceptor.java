package cn.zy2018.myadmin.interceptor;

import cn.zy2018.myadmin.annotation.AuthToken;
import cn.zy2018.myadmin.data.Result;
import cn.zy2018.myadmin.data.ResultCode;
import cn.zy2018.myadmin.mapper.UserMapper;
import cn.zy2018.myadmin.service.api.RedisService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.Enumeration;

public class AuthTokenInterceptor implements HandlerInterceptor {


    @Autowired
    RedisService redisService;
    /**
     * 预处理回调方法，实现处理器的预处理（如检查登陆），第三个参数为响应的处理器，自定义Controller
     * 返回值：
     * true表示继续流程（如调用下一个拦截器或处理器）；
     * false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应；
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        //将头部信息都转换成map
        String token = httpServletRequest.getParameter("token");

        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();

        //检查有没有需要用户权限的注解
        AuthToken authToken = method.getAnnotation(AuthToken.class);

        //不需要授权的接口直接返回true...其他的接口均要验证
        if(null != authToken && !authToken.required()){
            return  true;
        }

        //token失效或者不存在
        if (!redisService.checkTokenIsExists(token)){
            // 设置格式为text/json
            httpServletResponse.setContentType("text/json");
            //设置字符集为'UTF-8'
            httpServletResponse.setCharacterEncoding("UTF-8");
            PrintWriter writer = httpServletResponse.getWriter();
            writer.println(new Result(ResultCode.USER_TOKEN_NOT_EXISTS,null).toJson());
            writer.flush();
            writer.close();
        }

        return true;
    }

    /**
     * 后处理回调方法，实现处理器的后处理（但在渲染视图之前），此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理，modelAndView也可能为null。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间，还可以进行一些资源清理，类似于try-catch-finally中的finally，但仅调用处理器执行链中
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

}
