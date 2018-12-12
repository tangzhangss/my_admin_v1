package zyrs.xyz.obadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zyrs.xyz.obadmin.bean.Menu;
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
    public List<Menu> getMenuByObId(Integer id){

        return  obMapper.getMenuByObId(id);
    }

    /**
     * 获取左侧菜单，管理员
     * @return
     */
    @Cacheable(value="cache", key = "targetClass + methodName")
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


}
