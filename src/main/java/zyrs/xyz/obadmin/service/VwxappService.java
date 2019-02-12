package zyrs.xyz.obadmin.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zyrs.xyz.obadmin.bean.WxappMember;
import zyrs.xyz.obadmin.mapper.VwxappMapper;

import java.util.List;

/**
 * Created by Administrator on 2019/2/11.
 */
@Service
public class VwxappService {

    @Autowired
    private VwxappMapper vwxappMapper;

    public List<WxappMember> getAllUserBaseInfo(String like) {

        //数据库是base64编码之后的 所以需要编码之后查询
        String nickname =like.equals("")?"%%":"%"+new String(Base64.encodeBase64(like.getBytes()))+"%";

        return vwxappMapper.getAllUserBaseInfo(like,nickname);
    }
}
