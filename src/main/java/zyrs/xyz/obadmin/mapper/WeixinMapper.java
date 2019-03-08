package zyrs.xyz.obadmin.mapper;

import org.apache.ibatis.annotations.Mapper;
import zyrs.xyz.obadmin.bean.WeixinTemplate;

/**
 * Created by Administrator on 2019/3/8.
 */
@Mapper
public interface WeixinMapper {
    WeixinTemplate getBuySuccessTemplateById(String id);
}
