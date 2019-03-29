package zyrs.xyz.obadmin.mapper;

import org.apache.ibatis.annotations.Mapper;
import zyrs.xyz.obadmin.bean.*;

import java.util.List;

@Mapper
public interface WxappMapper {
    Wxapp getWxappInfoByObId(Integer obId);


    void addOrUpdateWxapp(Wxapp wxapp);

    void addNewBannerModal(Banner banner);

    List<Banner> getWxappBannerByOid(Integer obId);
    

    void modifyBannerModalWithDescr(Integer id, String value);

    void modifyBannerModalWithWidth(Integer id, String value);

    void modifyBannerModalWithHeight(Integer id, String value);

    void deleteBannerModal(Integer id);

    void addNewBannerChild(BannerChild bannerChild);

    void deleteBannerChild(Integer id);

    void updateMerchant(WxappMerchant wxappMerchant);

    WxappMerchant getWxappMerchantInfo(Integer obId);

    void insertOrUpdateMember(WxappMember wxappMember);

    WxappMember getWxappMember(String openid, Integer oid);

    void updateWeiXinOpenid(String openid, Integer id);

    String getUserWxopenidById(Integer id);

    void modifyUserBank(WxappBank wxappBank);

    WxappBank getUserBank(String openid,Integer oid);

    Double getMemberBlanceByOpenidAndOid(String openid,Integer oid);

    WxappMember getMemberBaseInfoByWxopenidAndOid(String wxopenid, Integer oid);

    void setMemberIsOnline(Integer id, int online);

    void insertWeixinOrder(WeixinOrder weixinOrder);

    Integer selectOrderStatusByOuttradeno(String outTradeNo);


    void setOrderPayAmount(String outTradeNo, double total_fee);

    WeixinOrder getWeixinOrderByOuttradeno(String outTradeNo);

    String getUserWxopenidByOpenidAndOid(String openid,Integer oid);

    void updateMemberBlanceByOpenidAndOid(String wxopenid,Integer oid, Double money);

    void insertMemberBlance(String wxopenid, Integer oid, Double balance);

    void setOrderStatus(WeixinOrder orderStatus);

    WeixinOrder getWeixinOrderById(Integer id);

    WxappMember getMemberBaseInfoByOpenidAndOid(String openid, Integer oid);

    List<WeixinTemplate> getWeixinTemplate(Integer obId);

    void insertOrUpdateTemplate(WeixinTemplate insertOrUpdateTemplate);

    void deleteTemplate(String id);
}
