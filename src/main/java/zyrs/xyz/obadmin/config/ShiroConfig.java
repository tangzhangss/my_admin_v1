package zyrs.xyz.obadmin.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zyrs.xyz.obadmin.realm.ShiroRealm;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();

        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        //登录请求都可以访问
        filterChainDefinitionMap.put("/user_login", "anon");
        //api接口
        filterChainDefinitionMap.put("/api/**", "anon");
        filterChainDefinitionMap.put("/weixin/**", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //记住我登录 就行了....
        filterChainDefinitionMap.put("/**", "user");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");

//        //未授权界面;
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 功能增强  shiro加密方式   次数
     * @return
     */
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher () {

        HashedCredentialsMatcher  credentialsMatcher = new HashedCredentialsMatcher();
        //加密方式
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密迭代次数
        credentialsMatcher.setHashIterations(2);
        //true加密用的hex编码，false用的base64编码
        credentialsMatcher.setStoredCredentialsHexEncoded(true);

        return credentialsMatcher;
    }
    @Bean("shiroRealm")
    public ShiroRealm ShiroRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher){
        ShiroRealm myShiroRealm = new ShiroRealm();
        myShiroRealm.setCredentialsMatcher(matcher);
        return myShiroRealm;
    }



    @Bean
    public SecurityManager securityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();

        securityManager.setRealm(shiroRealm);
        //注入记住我管理器;
        securityManager.setRememberMeManager(rememberMeManager());

        return securityManager;
    }

    /**
     * cookie对象;
     * 记住密码实现起来也是比较简单的，主要看下是如何实现的。
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie(){

        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){

        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }

    /**
     * 系统自带的Realm管理，主要针对多realm
     *
     * 如果只有一个realm最好不要配置，因为认证器不一样，，可自己重写modularRealmAuthenticator的核心方法
     * */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator(){

        ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();

        //设置认证策略 默认AtLeastOneSuccessfulStrategy
        //(1)FirstSuccessfulStrategy:只要有一个Realm验证成功即可，只返回第一个Realm身份验证成功的认证

        //(2)AtLeatOneSuccessfulStrategy:只要有一个Realm验证成功即可，和FirstSuccessfulStrategy不同，将
        //返回所有Realm身份校验成功的认证信息

        //(3)AllSuccessfulStrategy:所有Realm验证成功才算成功，且返回所有Realm身份认证成功的认证信息，如
        //果有一个失败就失败了。

        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());

        return modularRealmAuthenticator;
    }



    /**
     *  开启shiro aop注解支持.  暂时不知道用什么用，先写下来
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }




}
