package zyrs.xyz.obadmin.mapper;

import org.apache.ibatis.annotations.Mapper;
import zyrs.xyz.obadmin.bean.Wxapp;

@Mapper
public interface WxappMapper {
    Wxapp getWxappInfoByObId(Integer obId);


    void addOrUpdateWxapp(Wxapp wxapp);
}
