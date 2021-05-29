package cn.zy2018.myadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.zy2018.myadmin.entity.*;
import cn.zy2018.myadmin.mapper.ObMapper;

import java.util.List;

@Service
public class ObService {

    @Autowired
    private ObMapper obMapper;

    /**
     * 获取项目的左侧菜单列表
     * 可能管理员管理——客户的项目 所以这个菜单不能缓存
     * @param id  项目id
     * @return
     */
    public List<Menu> getMenuByObId(Integer id){
        return  obMapper.getMenuByObId(id);
    }

    /**
     * 获取左侧菜单，管理员
     * @return
     */
   // @Cacheable(value="cache", key = "targetClass + '_getMenu'")
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
    //@CacheEvict(value="cache",key="targetClass + '_getMenu'")
    public void add_or_update_menu(Menu menu) {
        obMapper.add_or_update_menu(menu);
    }
    /**
     * 插入更新二级菜单
     * @param menuSecond
     */
    //@CacheEvict(value="cache",key="targetClass + '_getMenu'")
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
    //@CacheEvict(value="cache",key="targetClass + '_getMenu'")
    public void del_menu_second(Integer id) {
        obMapper.del_menu_second(id);
    }
    /**
     * 删除一级菜单...
     * @param id
     */
    //@CacheEvict(value="cache",key="targetClass + '_getMenu'")
    public void del_menu(Integer id) {
        obMapper.del_menu(id);
    }


    /**
     * 获取所有项目信息
     *
     * 刷新项目的状态 正常 过期 大于 30天 放弃
     * @return
     */
    public List<Ob> get_all_project_list(){
        obMapper.flush_project_status();

        return obMapper.get_all_project_list();
    }

    /**
     * 增加或者更新 项目
     * @param ob 项目信息-对象
     */
    public void add_or_update_project(Ob ob) {
        obMapper.add_or_update_project(ob);
    }

    public void delete_project(Integer id) {
        obMapper.delete_project(id);
    }

    /**
     * 并不支持重新开始算之后,接着算
     * @param id
     * @param count
     */
    public void project_relay(Integer id, Integer count) {
        obMapper.project_relay(id,count);
    }

    public List<MenuProject> getMenuProjectsList(){
        return obMapper.getMenuProjectsList();
    }

    /**
     * 增加 菜单 -项目 关联
     * @param mid  菜单id
     * @param oid  项目id
     * @return
     */
    public int addMenuProject(Integer mid, Integer oid) {

        //判断是否存在此项目
        if(obMapper.getObCountInfoById(oid) < 1){
            return 3;
        }

        //查看是否存在此关联
        if(obMapper.getMenuProjectCountByMidAndOid(mid,oid) > 0){
            return 2;
        }

        obMapper.addMenuProject(mid,oid);

        return 1;
    }

    public void delMenuProject(Integer id) {
        obMapper.delMenuProject(id);
    }

    /**
     * 获取 项目的 id logo
     * @param oid 项目id
     * @return
     */
    public Ob getObInfoById(Integer oid) {
        return obMapper.getObInfoById(oid);
    }

    public void modifyLogo(Integer id, String logo) {
        obMapper.modifyLogo(id,logo);
    }


}
