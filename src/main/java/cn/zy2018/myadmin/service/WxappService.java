package cn.zy2018.myadmin.service;

import cn.zy2018.myadmin.entity.*;
import cn.zy2018.myadmin.mapper.WxappMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

         //获取返回
        WxappMember wxappMember1 = wxappMapper.getWxappMember(wxappMember.getOpenid(),wxappMember.getOid());

        if(wxappMember1 == null){
            //设置id 因为没有ID为插入 二不是更新
            wxappMapper.insertOrUpdateMember(wxappMember);
            wxappMember = wxappMapper.getWxappMember(wxappMember.getOpenid(),wxappMember.getOid());
        }else{
            wxappMember.setId(wxappMember1.getId());
            wxappMember.setContact(wxappMember1.getContact());
            wxappMember.setIdentity(wxappMember1.getIdentity());
            wxappMember.setWxopenid(wxappMember1.getWxopenid());
            wxappMember.setRealavatars(wxappMember1.getRealavatars());
            wxappMember.setRealname(wxappMember1.getRealname());

            wxappMapper.insertOrUpdateMember(wxappMember);
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
       wxappMapper.insertOrUpdateBankUserBank(wxappBank);
    }
    public WxappBank getUserBank(String openid,Integer oid) {
        return wxappMapper.getUserBank(openid,oid);
    }

    public WxappMember getMemberBaseInfoByWxopenidAndOid(String wxopenid,Integer oid) {
        return wxappMapper.getMemberBaseInfoByWxopenidAndOid(wxopenid,oid);
    }

    public WxappMember getMemberBaseInfoByOpenidAndOid(String openid, int oid) {
        return wxappMapper.getMemberBaseInfoByOpenidAndOid(openid,oid);
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

    public List<WxappMember> getMemberUserInfoByLikeAndOid(String like, Integer oid) {
        like = "%"+like+"%";
        return wxappMapper.getMemberUserInfoByLikeAndOid(like,oid);
    }

    public void insertOrUpdateMember(WxappMember wxappMember) {

        WxappMember member = wxappMapper.getMemberBaseInfoByOpenidAndOid(wxappMember.getOpenid(),wxappMember.getOid());

        if(member!=null){
            wxappMember.setId(member.getId());
        }
        wxappMapper.insertOrUpdateMember(wxappMember);
    }

    public List<WeixinOrder> getUserOrder(String like, Integer oid) {
        if(StringUtils.isNotBlank(like)){
            return wxappMapper.getUserOrder(like,oid);
        }else{
            return wxappMapper.getAllUserOrder(oid);
        }

    }

    public void updateMemberContactById(Integer id, String text) {
          wxappMapper.updateMemberContactById(id,text);
    }

    public WxappMember getMemberBaseInfoById(int pid) {
        return wxappMapper.getMemberBaseInfoById(pid);
    }

    public void insertOrUpdateBank(WxappBank wxappBank) {
        //新增
        wxappMapper.insertOrUpdateBankUserBank(wxappBank);
    }

    public WxappMember insertMemberAndReturnData(WxappMember wxappMember) {
        wxappMapper.insertMemberWithSimple(wxappMember);
        return wxappMapper.getWxappMember(wxappMember.getOpenid(),wxappMember.getOid());
    }

    public Aliyun getAliyunConfByObid(Integer obId) {
        return wxappMapper.selectAliyunConfByObid(obId);
    }

    public void addAliyunConf(Integer obId) {
        wxappMapper.insertAliyunConf(obId);
    }

    public void modifyAliyunConfOss(Aliyun aliyun) {
        wxappMapper.updateAliyunConfOss(aliyun);
    }

    public Integer getMemberIdentityById(Integer id) {
       return wxappMapper.selectMemberIdentityById(id);
    }
}
