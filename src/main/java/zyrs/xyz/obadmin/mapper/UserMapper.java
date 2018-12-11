package zyrs.xyz.obadmin.mapper;

import org.apache.ibatis.annotations.Mapper;
import zyrs.xyz.obadmin.bean.User;

@Mapper
public interface UserMapper {
    //查询用户信息—登录认证
    User getUserByUsername(String username);
}
