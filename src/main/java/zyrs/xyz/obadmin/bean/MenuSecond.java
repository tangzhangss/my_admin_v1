package zyrs.xyz.obadmin.bean;

import java.io.Serializable;

/**
 * 左侧菜单 二级
 */
public class  MenuSecond implements Serializable {

    //序列化ID
    private static final long serialVersionUID = 1;

    private Integer id;
    private String name;
    private String url;


    public MenuSecond(){
        //...
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MenuSecond{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
