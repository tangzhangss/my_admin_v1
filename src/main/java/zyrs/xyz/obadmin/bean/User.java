package zyrs.xyz.obadmin.bean;

/**
 * 用户表
 *
 * 一个用户可有多个项目
 */
public class User {

    private Integer id;//用户id
    private String username;//账号，手机号，只能是数字
    private String password;//密码
    private Integer obId;//项目Id

    private String rememberMe;//记住密码

    private String  logo="";//当前项目的logo


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", obId=" + obId +
                ", rememberMe='" + rememberMe + '\'' +
                ", logo='" + logo + '\'' +
                '}';
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

    public void setObId(Integer obId) {
        this.obId = obId;
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

    public Integer getObId() {
        return obId;
    }
}
