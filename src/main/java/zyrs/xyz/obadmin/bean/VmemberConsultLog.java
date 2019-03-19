package zyrs.xyz.obadmin.bean;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by Administrator on 2019/3/8.
 * 聊天记录....
 */
public class VmemberConsultLog {

    private Integer id;
    private Integer consultId;
    private Integer ctype;
    private String message;
    private String messageDecode;//解码的消息
    private String messageEncode;//编码的
    private String image;
    private String video;//语音消息
    private Integer identity;//发送消息的省份 1患者 2医生
    private String replytime;//回复时间 时间戳
    private Integer isread;//是否已读 0未读 1已读

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConsultId() {
        return consultId;
    }

    public void setConsultId(Integer consultId) {
        this.consultId = consultId;
    }

    public Integer getCtype() {
        return ctype;
    }

    public void setCtype(Integer ctype) {
        this.ctype = ctype;
    }

    public String getMessage() {
        return message;
    }
    public String getMessageDecode() {
        //base64编码 解码
        if(message!=null){
            return new String(Base64.decodeBase64(message));
        }else{
            return message;
        }
    }

    public String getMessageEncode() {
        //base64编码
        if(message!=null){
           return Base64.encodeBase64String(message.getBytes());
        }

        return null;

    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public String getReplytime() {

        return replytime;
    }

    public void setReplytime(String replytime) {
        //去掉timestamp后面的0_前端app显示就是去掉秒显示
        this.replytime = replytime.substring(0,replytime.lastIndexOf("."));
    }

    public Integer getIsread() {
        return isread;
    }

    public void setIsread(Integer isread) {
        this.isread = isread;
    }

    @Override
    public String toString() {
        return "VmemberConsultLog{" +
                "id=" + id +
                ", consultId=" + consultId +
                ", ctype=" + ctype +
                ", message='" + message + '\'' +
                ", image='" + image + '\'' +
                ", video='" + video + '\'' +
                ", identity='" + identity + '\'' +
                ", replytime='" + replytime + '\'' +
                ", isread=" + isread +
                '}';
    }
}
