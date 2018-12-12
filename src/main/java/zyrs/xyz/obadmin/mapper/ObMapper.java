package zyrs.xyz.obadmin.mapper;

import org.apache.ibatis.annotations.Mapper;
import zyrs.xyz.obadmin.bean.Menu;
import zyrs.xyz.obadmin.bean.Ob;

import java.util.List;

@Mapper
public interface ObMapper{
    //管理员
    List<Menu> getMenuWithAdmin();

    //客户
    List<Menu> getMenuByObId(Integer id);

    //客户项目信息
    List<Ob> getObInfoByUserId(Integer id);


}
