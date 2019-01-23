package zyrs.xyz.obadmin.bean;

import java.util.List;

/**
 * 轮播图模块
 */
public class Banner{
    private Integer id;//id
    private String number;//编号 api 调用_唯一标识_where条件 需要加上limit 0,1
    private String descr;//轮播图使用场景说明

    private String width;//轮播图宽度_带上单位
    private String height;//轮播图高度_带上单位
    private Integer obId;//项目id
    private List<BannerChild> bannerChild;//模块下的图片

    public Integer getObId() {
        return obId;
    }

    public void setObId(Integer obId) {
        this.obId = obId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String desc) {
        this.descr = desc;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public List<?> getBannerChild() {
        return bannerChild;
    }

    public void setBannerChild(List<BannerChild> bannerChild) {
        this.bannerChild = bannerChild;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", descr='" + descr + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", obId=" + obId +
                ", bannerChild=" + bannerChild +
                '}';
    }
}
