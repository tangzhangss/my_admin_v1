package cn.zy2018.myadmin.entity;

/**
 * Created by Administrator on 2019/3/8.
 * 微信模板消息 template value
 */
public class WeixinTemplateValue {
    private String value;
    private  String color;

    public WeixinTemplateValue(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    //返回json格式数据
    public String toJson(){
         StringBuffer str = new StringBuffer();
         str.append("{");
         str.append("\"value\":\""+value+"\",");
         str.append("\"color\":\""+color+"\"");
         str.append("}");
        return str.toString();
    }
}
