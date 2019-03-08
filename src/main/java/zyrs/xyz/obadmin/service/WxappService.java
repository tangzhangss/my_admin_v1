package zyrs.xyz.obadmin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zyrs.xyz.obadmin.bean.*;
import zyrs.xyz.obadmin.mapper.UserMapper;
import zyrs.xyz.obadmin.mapper.VwxappMapper;
import zyrs.xyz.obadmin.mapper.WxappMapper;
import zyrs.xyz.obadmin.utils.HttpRequest;
import zyrs.xyz.obadmin.utils.MD5Util;

import java.io.IOException;
import java.util.List;

@Service
public class WxappService {

    @Autowired
    private WxappMapper wxappMapper;


    public Wxapp getWxappInfoByObId(Integer obId) {
        return wxappMapper.getWxappInfoByObId(obId);
    }

    public void addOrUpdateWxapp(Wxapp wxapp) {
        wxappMapper.addOrUpdateWxapp(wxapp);
    }


    public void addNewBannerModal(Banner banner) {
        wxappMapper.addNewBannerModal(banner);
    }

    public List<Banner> getWxappBannerByOid(Integer obId) {
        return wxappMapper.getWxappBannerByOid(obId);
    }


    public void modifyBannerModal(Integer id, String type, String value) {
        if(type.equals("descr")){
            wxappMapper.modifyBannerModalWithDescr(id,value);
        }else if(type.equals("width")){
            wxappMapper.modifyBannerModalWithWidth(id,value);
        }else if(type.equals("height")){
            wxappMapper.modifyBannerModalWithHeight(id,value);
        }

    }

    public void deleteBannerModal(Integer id) {
        wxappMapper.deleteBannerModal(id);
    }

    public void addNewBannerChild(BannerChild bannerChild) {
        wxappMapper.addNewBannerChild(bannerChild);
    }

    public void deleteBannerChild(Integer id) {
        wxappMapper.deleteBannerChild(id);
    }

    public void updateMerchant(WxappMerchant wxappMerchant) {
        wxappMapper.updateMerchant(wxappMerchant);
    }

    public WxappMerchant getWxappMerchantInfo(Integer obId) {
        return wxappMapper.getWxappMerchantInfo(obId);
    }


    @Transactional
    public WxappMember insertOrUpdateMemberAndReturnData(WxappMember wxappMember) {

        //base64
        wxappMember.setNicknameEncodeBase64();
        //先查询
        WxappMember wxappMember1 = wxappMapper.getWxappMember(wxappMember.getOpenid(),wxappMember.getOid());
        System.out.println("member"+wxappMember1);

        if(wxappMember1!=null){

            //返回ID和身份
            wxappMember.setId(wxappMember1.getId());
            wxappMember.setIdentity(wxappMember1.getIdentity());

            //必须要有 微信网页授权_返回时需要 更新插入不需要
            wxappMember.setWxopenid(wxappMember1.getWxopenid());
        }

        wxappMapper.insertOrUpdateMember(wxappMember);

        //更新or插入
        return wxappMember;
    }

    public void updateWeiXinOpenid(String openid, Integer id) {
        wxappMapper.updateWeiXinOpenid(openid,id);
    }

    public String getUserWxopenidById(Integer id) {
      return   wxappMapper.getUserWxopenidById(id);
    }


    public void modifyUserBank(WxappBank wxappBank) {
        WxappBank wxappBankTemp;

       //查询openid是否存在
       if((wxappBankTemp = getUserBank(wxappBank.getOpenid(),wxappBank.getOid())) != null){
           wxappBank.setId(wxappBankTemp.getId());
       }


        wxappMapper.modifyUserBank(wxappBank);
    }
    public WxappBank getUserBank(String openid,Integer oid) {
        return wxappMapper.getUserBank(openid,oid);
    }

    public WxappMember getMemberBaseInfoByWxopenidAndOid(String wxopenid,Integer oid) {
        return wxappMapper.getMemberBaseInfoByWxopenidAndOid(wxopenid,oid);
    }
}
