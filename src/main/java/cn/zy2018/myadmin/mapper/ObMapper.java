package cn.zy2018.myadmin.mapper;

import org.apache.ibatis.annotations.Mapper;
import cn.zy2018.myadmin.entity.*;

import java.util.List;

@Mapper
public interface ObMapper{
    //管理员
    List<Menu> getMenuWithAdmin();

    //所有
    List<Menu> getMenu();

    //客户
    List<Menu> getMenuByObId(Integer id);

    //客户项目信息
    List<Ob> getObInfoByUserId(Integer id);


    void add_or_update_menu(Menu menu);

    void add_or_update_menuSecond(MenuSecond menuSecond);

    void del_menu_second(Integer id);

    void del_menu(Integer id);

    List<Ob> get_all_project_list();

    void add_or_update_project(Ob ob);

    void delete_project(Integer id);

    void project_relay(Integer id, Integer count);

    void flush_project_status();

    List<MenuProject> getMenuProjectsList();

    void addMenuProject(Integer mid, Integer oid);

    Integer getMenuProjectCountByMidAndOid(Integer mid, Integer oid);

    void delMenuProject(Integer id);

    Integer getObCountInfoById(Integer oid);

    Ob getObInfoById(Integer oid);

    void modifyLogo(Integer id, String logo);

}
