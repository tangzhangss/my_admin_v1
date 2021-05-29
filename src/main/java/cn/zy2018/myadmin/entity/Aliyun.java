package cn.zy2018.myadmin.entity;

public class Aliyun {
    /*
    oss其他配置不支持修改
    bucket my1admin my1adminQuanv(根据项目自行设置)
    图片处理默认 my1admin-picture-style
    * */
    private String ossAccessKeyID;
    private String ossAccessKeySecret;
    private String ossEndpoint;
    private Integer obid;//项目id

    public Aliyun(){

    }

    public Integer getObid() {
        return obid;
    }

    public void setObid(Integer obid) {
        this.obid = obid;
    }

    public String getOssAccessKeyID() {
        return ossAccessKeyID;
    }

    public void setOssAccessKeyID(String ossAccessKeyID) {
        this.ossAccessKeyID = ossAccessKeyID;
    }

    public String getOssAccessKeySecret() {
        return ossAccessKeySecret;
    }

    public void setOssAccessKeySecret(String ossAccessKeySecret) {
        this.ossAccessKeySecret = ossAccessKeySecret;
    }

    public String getOssEndpoint() {
        return ossEndpoint;
    }

    public void setOssEndpoint(String ossEndpoint) {
        this.ossEndpoint = ossEndpoint;
    }
}
