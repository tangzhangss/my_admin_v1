package zyrs.xyz.obadmin.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zyrs.xyz.obadmin.bean.User;
import zyrs.xyz.obadmin.utils.AliyunOss;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
@SessionAttributes({"current_user","menuList"})
@RequestMapping("/util")
public class UtilController {

    /**
     * 上传图片
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

    /**
     * 上传cert文件 到 服务器本地
     * @return json形式
     */
    @RequestMapping(value="upload_local_cert_file",method= RequestMethod.POST)
    public String uploadLocalCertFile(@RequestParam("file") MultipartFile file,@RequestParam("path")String path, Map<String, Object> map){
        //获取文件的名字
        String filename = file.getOriginalFilename();

        //服务器绝对路径
        String filepath = path+"/"+filename;

        File newfile = new File(filepath);

        //创建文件夹
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //创建文件 | 判断是否存在 | 权限问题
        if(!newfile.exists()){
            try {
                newfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
               return "{\"data\":\""+e.getMessage()+"\"}";
            }
        }

        try {
            file.transferTo(newfile);
            return "{\"data\":"+ "\""+filepath+"\"}";
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"data\":\""+ e.getMessage()+"\"}";
        }
    }

}
