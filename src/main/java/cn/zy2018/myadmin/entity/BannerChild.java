package cn.zy2018.myadmin.entity;

public class BannerChild {
    private Integer id;
    private String url;//图片链接
    //一下同 微信小程序 navigator组件
    private String target;//在哪个目标上发生跳转，默认当前小程序，可选值self/miniProgram
    private String navurl;//跳转链接

    private String appId;//小程序appid
    private String path;//打开小程序的路径
    private String extraData;//参数 js{}格式

    private Integer bid;//所属轮播图模块id

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getNavurl() {
        return navurl;
    }

    public void setNavurl(String navurl) {
        this.navurl = navurl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    @Override
    public String toString() {
        return "BannerChild{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", target='" + target + '\'' +
                ", navurl='" + navurl + '\'' +
                ", appId='" + appId + '\'' +
                ", path='" + path + '\'' +
                ", extraData='" + extraData + '\'' +
                ", bid=" + bid +
                '}';
    }
}
