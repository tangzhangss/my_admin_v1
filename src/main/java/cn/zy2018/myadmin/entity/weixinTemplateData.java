package cn.zy2018.myadmin.entity;

/**
 * Created by Administrator on 2019/3/8.
 * 微信公众号模板 模板消息数据
 */
public class weixinTemplateData{

    //购买成功通知
    private WeixinTemplateValue name;
    private WeixinTemplateValue remark;


    public WeixinTemplateValue getName() {
        return name;
    }

    public void setName(WeixinTemplateValue name) {
        this.name = name;
    }

    public WeixinTemplateValue getRemark() {
        return remark;
    }

    public void setRemark(WeixinTemplateValue remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "weixinTemplateData{" +
                "name=" + name +
                ", remark=" + remark +
                '}';
    }
}
