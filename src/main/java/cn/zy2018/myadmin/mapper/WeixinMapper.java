package cn.zy2018.myadmin.mapper;

import org.apache.ibatis.annotations.Mapper;
import cn.zy2018.myadmin.entity.WeixinTemplate;

/**
 * Created by Administrator on 2019/3/8.
 */
@Mapper
public interface WeixinMapper {
    WeixinTemplate getWeiXinTemplateById(String id);
}
