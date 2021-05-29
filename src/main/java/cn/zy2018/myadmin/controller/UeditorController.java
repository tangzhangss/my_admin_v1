package cn.zy2018.myadmin.controller;

import cn.zy2018.myadmin.entity.User;
import cn.zy2018.myadmin.utils.AliyunOss;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/29.
 */
@RestController
@SessionAttributes({"current_user","menuList"})
@RequestMapping("/ueditor")
public class UeditorController {

    //ueditr后端配置
    @RequestMapping("")
    public String ueditorInit(@RequestParam("action") String action, @RequestParam("noCache") String nocache, HttpServletRequest request, HttpServletResponse response) {

        switch (action) {
            case "config":
                try {
                    response.setContentType("application/json;charset=utf-8");
                    Resource resource = new ClassPathResource("/static/ueditor/jsp/config.json");
                    File file = resource.getFile();
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    return stringBuilder.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                    return e.getMessage();
                }

            default: return "";
        }
    }


    /**
     * 上传图片
     * @return json形式
     */
    @RequestMapping(value="upload_image",method= RequestMethod.POST)
    public String uploadImage(@RequestParam("upfile") MultipartFile file, Map<String, Object> map){
        User user = (User) map.get("current_user");
        String res =  AliyunOss.upload_picture("picture/"+user.getUsername(),file);
        if(res.equals("error")){
            return "{" +
                    "\"state\":\"ERROR\"," +
                    "\"url\":\"\"," +
                    "\"title\":\"图片上传失败\"," +
                    "\"original\":\"图片上传失败\"" +
                    "}";
        }else{
            return  "{" +
                    "\"state\":\"SUCCESS\"," +
                    "\"url\":\""+res+"\","+
                    "\"title\":\""+file.getOriginalFilename()+"\","+
                    "\"original\":\""+file.getOriginalFilename()+"\""+
                    "}";
        }
    }
}
