package zyrs.xyz.obadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zyrs.xyz.obadmin.bean.WeixinTemplate;
import zyrs.xyz.obadmin.mapper.WeixinMapper;

/**
 * Created by Administrator on 2019/3/8.
 */
@Service
public class WeixinService {

    @Autowired
    private WeixinMapper weixinMapper;

    /**
     * 购买成功 模板消息 对应id 不可变  因为id是主键 所以不需要项目id即可区别任意东西
     * @return
     */
    public WeixinTemplate getBuySuccessTemplateById(String id) {
        return weixinMapper.getBuySuccessTemplateById(id);
    }
}
