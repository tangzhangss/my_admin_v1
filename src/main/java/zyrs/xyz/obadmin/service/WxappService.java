package zyrs.xyz.obadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zyrs.xyz.obadmin.bean.User;
import zyrs.xyz.obadmin.bean.Wxapp;
import zyrs.xyz.obadmin.mapper.UserMapper;
import zyrs.xyz.obadmin.mapper.WxappMapper;
import zyrs.xyz.obadmin.utils.MD5Util;

import java.util.List;

@Service
public class WxappService {

    @Autowired
    private WxappMapper wxappMapper;


    public Wxapp getWxappInfoByObId(Integer obId) {
        return wxappMapper.getWxappInfoByObId(obId);
    }
}
