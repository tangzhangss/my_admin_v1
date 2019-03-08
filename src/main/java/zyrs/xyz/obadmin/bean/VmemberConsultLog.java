package zyrs.xyz.obadmin.bean;

/**
 * Created by Administrator on 2019/3/8.
 * 聊天记录....
 */
public class VmemberConsultLog {

    private Integer id;
    private Integer consultId;
    private Integer ctype;
    private String message;
    private String image;
    private String video;//语音消息
    private String identity;//发送消息的省份 1患者 2医生
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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getReplytime() {
        return replytime;
    }

    public void setReplytime(String replytime) {
        this.replytime = replytime;
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
