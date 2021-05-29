package cn.zy2018.myadmin.data;

public enum ResultCode{

    SUCCESS(1,"成功"),
    //1001-1999 参数错误

    //2001-2999 用户错误
    USER_LOGIN_ERROR(2001,"用户名或密码错误"),

    USER_NOT_EXISTS(2002,"用户不存在"),

    USER_PASSWORD_ERROR(2003,"密码错误"),

    USER_ALREADY_EXISTS(2004,"用户名已存在"),

    USER_TOKEN_NOT_EXISTS(2005,"用户Token不存在或者已过期"),

    //3001-3999 服务器错误
    System_ERROR(3001,"服务器错误");

    private Integer code;
    private String message;

    ResultCode(Integer code,String message){
      this.code = code;
      this.message = message;
    }
    public Integer code(){
        return  this.code;
    }
    public String message(){
        return  this.message;
    }
}
