package zyrs.xyz.obadmin.bean;

import java.io.Serializable;

/**
 * 用户表
 *
 * 一个用户可有多个项目
 */
public class User implements Serializable {

    //序列化ID
    private static final long serialVersionUID = 1;

    private Integer id;//用户id
    private String username;//账号，手机号，只能是数字
    private String password;//密码
    private Integer level;//用户类型 1管理 2客户

    private String rememberMe;//记住密码

    private String  logo="";//当前项目的logo
    private Integer obId;//当前项目的id


    private Integer selectedMenuId=0;//当前被选中的菜单id

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", level=" + level +
                ", rememberMe='" + rememberMe + '\'' +
                ", logo='" + logo + '\'' +
                ", obId=" + obId +
                ", selectedMenuId=" + selectedMenuId +
                '}';
    }

    public Integer getObId() {
        return obId;
    }

    public void setObId(Integer obId) {
        this.obId = obId;
    }

    public Integer getSelectedMenuId() {
        return selectedMenuId;
    }

    public void setSelectedMenuId(Integer selectedMenuId) {
        this.selectedMenuId = selectedMenuId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level==null?2:level;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
