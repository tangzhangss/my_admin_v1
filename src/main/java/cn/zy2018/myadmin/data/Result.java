package cn.zy2018.myadmin.data;

import io.netty.handler.codec.json.JsonObjectDecoder;
import net.sf.json.JSONObject;

import java.io.Serializable;

public class Result implements Serializable {
    private  Integer code;
    private String message;
    private Object data;

    public Result(ResultCode resultCode,Object data){
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.data = data;
    }

    public Result(Exception e){
        this.code = ResultCode.System_ERROR.code();
        this.data = e.getMessage();
        this.message = ResultCode.System_ERROR.message();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("code",this.code);
        json.put("message",this.message);
        json.put("data",this.data);

        return  json;
    }
}
