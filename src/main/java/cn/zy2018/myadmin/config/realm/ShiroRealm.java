package cn.zy2018.myadmin.config.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import cn.zy2018.myadmin.entity.User;
import cn.zy2018.myadmin.mapper.UserMapper;

public class ShiroRealm  extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    /**
     * 授权  此次不适用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("realm 授权...");

        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.authenticationToken 转化为 UsernamePasswordToken
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //2.从UsernamePasswordToken中获取username
        String username = token.getUsername();

        //3.从数据库中取出用户信息
        User user = userMapper.getUserByUsername(username);
        /**
         * 中间可进行验证，这里给出事例是：用户名为admin 密码 admin
         *
         * 如果用户不存在抛出UnknownAccountException(); 由controller接受
         */
        if(null == user){
            throw new UnknownAccountException();
        }

        //然后进行密码比对--从数据库中取出用户名和密码
        Object principal = user.getUsername();
        Object credentials = user.getPassword();//admin 加密两次的值
        String realmName = getName();

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials,realmName);
        return info;
    }
    public static void main(String args[]){
        String algorithmName ="MD5";
        String source = "188632";
        Object salt = null;
        int hashIterations = 2;
        SimpleHash  s= new SimpleHash(algorithmName, source, salt, hashIterations);
        System.out.println(s.toString());
    }
}
