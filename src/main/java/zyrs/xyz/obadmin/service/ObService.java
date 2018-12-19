package zyrs.xyz.obadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zyrs.xyz.obadmin.bean.Menu;
import zyrs.xyz.obadmin.bean.MenuSecond;
import zyrs.xyz.obadmin.bean.Ob;
import zyrs.xyz.obadmin.mapper.ObMapper;

import java.util.List;

@Service
public class ObService {

    @Autowired
    private ObMapper obMapper;

    /**
     * 获取项目的左侧菜单列表
     * @param id  用户id
     * @return
     */
    @Cacheable(value="cache", key = "targetClass + '_getMenu'")
    public List<Menu> getMenuByObId(Integer id){

        return  obMapper.getMenuByObId(id);
    }

    /**
     * 获取左侧菜单，管理员
     * @return
     */
    @Cacheable(value="cache", key = "targetClass + '_getMenu'")
    public List<Menu> getMenuWithAdmin() {
        return  obMapper.getMenuWithAdmin();
    }

    /**
     * 获取项目基本信息
     * @param id 用户id
     * @return
     */
    public List<Ob> getObInfoByUserId(Integer id) {
        return obMapper.getObInfoByUserId(id);
    }

    /**
     * 插入更新一级菜单
     * @param menu
     */
    @CacheEvict(value="cache",key="targetClass + '_getMenu'")
    public void add_or_update_menu(Menu menu) {
        obMapper.add_or_update_menu(menu);
    }
    /**
     * 插入更新二级菜单
     * @param menuSecond
     */
    @CacheEvict(value="cache",key="targetClass + '_getMenu'")
    public void add_or_update_menu_second(MenuSecond menuSecond) {
        obMapper.add_or_update_menuSecond(menuSecond);
    }

    /**
     * 获取所有 菜单 不区分管理员和客户
     * @return
     */
    public List<Menu>  getMenu() {
        return obMapper.getMenu();
    }

    /**
     * 删除二级菜单...
     * @param id
     */
    @CacheEvict(value="cache",key="targetClass + '_getMenu'")
    public void del_menu_second(Integer id) {
        obMapper.del_menu_second(id);
    }
    /**
     * 删除一级菜单...
     * @param id
     */
    @CacheEvict(value="cache",key="targetClass + '_getMenu'")
    public void del_menu(Integer id) {
        obMapper.del_menu(id);
    }


    /**
     * 获取所有项目信息
     * @return
     */
    public List<Ob> get_all_project_list(){
        return obMapper.get_all_project_list();
    }
}
