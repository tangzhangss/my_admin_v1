package cn.zy2018.myadmin.mapper;

import org.apache.ibatis.annotations.Mapper;
import cn.zy2018.myadmin.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    //查询用户信息—登录认证
    User getUserByUsername(String username);

    List<User> get_all_user_list();

    void deleteUserById(Integer id);

    void addOrUpdateUser(User user);

    void updatePrevObId(Integer id,Integer oid);

    Integer getObjectByUserIdAndOid(Integer id, Integer oid);
}
