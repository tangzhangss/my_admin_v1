package zyrs.xyz.obadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zyrs.xyz.obadmin.bean.User;
import zyrs.xyz.obadmin.mapper.UserMapper;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public User getUserByUserName(String username) {
       return userMapper.getUserByUsername(username);
    }
}
