package zyrs.xyz.obadmin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import zyrs.xyz.obadmin.bean.User;
import zyrs.xyz.obadmin.bean.Wxapp;
import zyrs.xyz.obadmin.bean.WxappMember;
import zyrs.xyz.obadmin.bean.WxappResult;
import zyrs.xyz.obadmin.service.ObService;
import zyrs.xyz.obadmin.service.WxappService;
import zyrs.xyz.obadmin.utils.WxappApiUtil;

/**
 * Created by Administrator on 2019/3/1.
 */
@RestController
@RequestMapping("/api/v")
public class VwxappApiController {

    @Autowired
    private WxappService wxappService;


    /**
     * 用户授权登录,
     * @param wxappMember 基本信息
     * @param code 用户登录凭证
     * @return
     */
    @RequestMapping("login")
    public WxappMember login(WxappMember wxappMember,@RequestParam("code")String code,@RequestParam("id")Integer id){

        Wxapp wxapp = wxappService.getWxappInfoByObId(id);

        WxappResult wxappResult = WxappApiUtil.authLogin(wxapp.getAppid(),wxapp.getSecret(),code);
        System.out.println(wxappResult);
        wxappMember.setOid(id);
        wxappMember.setOpenid(wxappResult.getOpenid());
        //根据openID 更新或者插入数据 并返回 数据

        WxappMember member = wxappService.insertOrUpdateMemberAndReturnData(wxappMember);

        return member;
    }

    @RequestMapping("get_user_wxopenid_by_id")
    public String getUserWxopenidById(@RequestParam("id")Integer id){

        String member = wxappService.getUserWxopenidById(id);

        return member;
    }

}
