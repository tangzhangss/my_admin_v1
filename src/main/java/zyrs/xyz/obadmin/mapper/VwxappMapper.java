package zyrs.xyz.obadmin.mapper;

import org.apache.ibatis.annotations.Mapper;
import zyrs.xyz.obadmin.bean.WxappMember;

import java.util.List;

/**
 * Created by Administrator on 2019/2/11.
 */
@Mapper
public interface VwxappMapper {
    List<WxappMember> getAllUserBaseInfo(String wxopenid,String nickname);
}
