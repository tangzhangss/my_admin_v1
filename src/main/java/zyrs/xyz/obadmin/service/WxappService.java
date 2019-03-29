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


         //获取返回
        WxappMember wxappMember1 = wxappMapper.getWxappMember(wxappMember.getOpenid(),wxappMember.getOid());

        if(wxappMember1 == null){
            //设置id 因为没有ID为插入 二不是更新
            wxappMapper.insertOrUpdateMember(wxappMember);
            wxappMember = wxappMapper.getWxappMember(wxappMember.getOpenid(),wxappMember.getOid());
        }else{
            wxappMember.setId(wxappMember1.getId());
            wxappMapper.insertOrUpdateMember(wxappMember);
            wxappMember.setIdentity(wxappMember1.getIdentity());
        }

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

    public void setMemberIsOnline(Integer id, int online) {
        wxappMapper.setMemberIsOnline(id,online);
    }

    public void insertWeixinOrder(WeixinOrder weixinOrder) {
        wxappMapper.insertWeixinOrder(weixinOrder);
    }

    public Integer selectOrderStatusByOuttradeno(String outTradeNo) {
        return wxappMapper.selectOrderStatusByOuttradeno(outTradeNo);
    }

    @Transactional
    public void setOrderPayAmount(String outTradeNo) {
        //加用户的余额
        //查询订单 openid > wxopenid 和 money
        WeixinOrder weixinOrder = wxappMapper.getWeixinOrderByOuttradeno(outTradeNo);

        //查询用户的wxopenid
        String wxopenid = wxappMapper.getUserWxopenidByOpenidAndOid(weixinOrder.getOpenid(),weixinOrder.getOid());

        //查询是否存在改患者的月记录查询ID
        Double money = wxappMapper.getMemberBlanceByOpenidAndOid(wxopenid,weixinOrder.getOid());

        if(money == null){
            //插入
            wxappMapper.insertMemberBlance(wxopenid,weixinOrder.getOid(),weixinOrder.getMoney());
        }else{
            //更新余额
            wxappMapper.updateMemberBlanceByOpenidAndOid(wxopenid,weixinOrder.getOid(),money+weixinOrder.getMoney());
        }

    }

    public void setOrderStatus(WeixinOrder orderStatus) {
        wxappMapper.setOrderStatus(orderStatus);
    }

    public Double getMemberBlanceByOpenidAndOid(String wxopenid, int oid) {
        return wxappMapper.getMemberBlanceByOpenidAndOid(wxopenid,oid);
    }

    public List<WeixinTemplate> getWeixinTemplate(Integer obId) {
        return wxappMapper.getWeixinTemplate(obId);
    }

    public void insertOrUpdateTemplate(WeixinTemplate insertOrUpdateTemplate) {
        wxappMapper.insertOrUpdateTemplate(insertOrUpdateTemplate);
    }

    public void deleteTemplate(String id) {
        wxappMapper.deleteTemplate(id);
    }
}
