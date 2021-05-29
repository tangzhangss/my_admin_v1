package cn.zy2018.myadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.zy2018.myadmin.entity.User;
import cn.zy2018.myadmin.mapper.UserMapper;
import cn.zy2018.myadmin.utils.MD5Util;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserByUserName(String username) {
       return userMapper.getUserByUsername(username);
    }


    /**
     * 获取所有客户的信息列表
     * @return
     */
    public List<User> get_all_user_list() {
        return userMapper.get_all_user_list();
    }

    /**
     * 更新用户信息——新增
     * @param user
     * @return  2 _新增操作_用户已存在 1正常
     */
    public Integer addOrUpdateUser(User user) {
        //加密密码
        user.setPassword(MD5Util.encryptPassword(user.getPassword()));

        if(user.getId() == null){
            //表示是新增操作——必须要检查用户是否存在
            if(getUserByUserName(user.getUsername()) != null){
                return 2;
            }
        }
        userMapper.addOrUpdateUser(user);

        return 1;
    }

    /**
     * 删除用户信息
     * @param id 用户id
     */
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }

    public void updatePrevObId(Integer id, Integer oid) {
        userMapper.updatePrevObId(id,oid);
    }

    public Integer getObjectByUserIdAndOid(Integer id, Integer oid) {
        return userMapper.getObjectByUserIdAndOid(id,oid);
    }
}
