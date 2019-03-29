package zyrs.xyz.obadmin.bean;

/**
 * Created by Administrator on 2019/3/8.
 * 微信模板消息类  公众号
 */
public class WeixinTemplate {
    private String id;//如 "quanvjk_buy_success"自定义字段
    private String templateId;//模板id
    private String url;// 模板消息详情链接
    private String data;//json数据
    private String remark;//备注——理解和微信公众平台模板标题对应
    private String touser;//用户的openID
    private String adminOpenid;//管理者微信openid
    private Integer oid;//项目id

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdminOpenid() {
        return adminOpenid;
    }

    public void setAdminOpenid(String adminOpenid) {
        this.adminOpenid = adminOpenid;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WeixinTemplate{" +
                "id='" + id + '\'' +
                ", templateId='" + templateId + '\'' +
                ", url='" + url + '\'' +
                ", data='" + data + '\'' +
                ", remark='" + remark + '\'' +
                ", touser='" + touser + '\'' +
                ", adminWxopenid='" + adminOpenid + '\'' +
                '}';
    }
}
