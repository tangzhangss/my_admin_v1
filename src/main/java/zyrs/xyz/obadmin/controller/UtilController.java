package zyrs.xyz.obadmin.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zyrs.xyz.obadmin.bean.User;
import zyrs.xyz.obadmin.utils.AliyunOss;

import java.util.Map;

@RestController
@SessionAttributes({"current_user","menuList"})
@RequestMapping("/util")
public class UtilController {

    /**
     * 上传图片
     * @param file
     * @param map
     * @return json形式
     */
    @RequestMapping(value="upload_picture",method= RequestMethod.POST)
    public String upload_picture(@RequestParam("file") MultipartFile file, Map<String, Object> map){

        //获取文件的名字
        String filename = file.getOriginalFilename();

        User user = (User) map.get("current_user");

        String res =  AliyunOss.upload_picture("picture/"+user.getUsername(),file);

        System.out.println("图片地址:"+res);

        if(res.equals("error")){
            return "{\"data\":\"图片上传失败\"}";
        }else{
            return "{\"data\":"+ "\""+res+"\"}";
        }
    }
}
