package zyrs.xyz.obadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zyrs.xyz.obadmin.bean.User;
import zyrs.xyz.obadmin.mapper.UserMapper;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Cacheable(value="cache", key = "targetClass + methodName + #p0")
    public User getUserByUserName(String username) {
       return userMapper.getUserByUsername(username);
    }
}
