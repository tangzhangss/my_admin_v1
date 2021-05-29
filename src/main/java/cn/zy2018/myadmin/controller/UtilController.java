package cn.zy2018.myadmin.controller;


import cn.zy2018.myadmin.entity.User;
import cn.zy2018.myadmin.data.Result;
import cn.zy2018.myadmin.data.ResultCode;
import cn.zy2018.myadmin.utils.AliyunOss;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
@SessionAttributes({"current_user","menuList"})
@RequestMapping("/util")
public class UtilController {

    /**
     * 上传图片_注意 请求参数name 是 file
     * @return json形式
     */
    @RequestMapping(value="upload_picture",method= RequestMethod.POST)
    public String upload_picture(@RequestParam("file") MultipartFile file, Map<String, Object> map){

        User user = (User) map.get("current_user");

        String res =  AliyunOss.upload_picture("picture/"+user.getUsername(),file);

        if(res.equals("error")){
            return "{\"data\":\"图片上传失败\"}";
        }else{
            return "{\"data\":"+ "\""+res+"\"}";
        }
    }


    /**
     * 上传图片_版本1 请求参数name 是 file
     * 20200313
     */
    @RequestMapping(value="upload_picture_1",method= RequestMethod.POST)
    public Result uploadPicture1(@RequestParam("file") MultipartFile file, Map<String, Object> map){
        User user = (User) map.get("current_user");
        String addr =  AliyunOss.upload_picture("picture/"+user.getUsername(),file);

        if(addr.equals("error")){
            return  new Result(ResultCode.System_ERROR,"图片上传失败");
        }else{
            return  new Result(ResultCode.SUCCESS,addr);
        }
    }

    /**
     * 上传视频 ！！！！不要再使用！！！
     * param id 视频id //上传进度条的id好  多个进度条同
     */
    @RequestMapping(value="upload_video",method= RequestMethod.POST)
    public String uploadVideo(@RequestParam(value = "id",defaultValue = "0",required = false) int id, @RequestParam("file") MultipartFile file, Map<String, Object> map,HttpServletRequest request){
        User user = (User) map.get("current_user");
        //aliyun上传进度条接口
        AliyunOss.PutObjectProgressListener progress = new AliyunOss.PutObjectProgressListener(request.getSession());
        progress.totalBytes = file.getSize();
        progress.id = id;

        String res =  AliyunOss.upload_video("video/"+user.getUsername(),file,progress);

        return res;
    }
    /**
     * 上传视频 返回值格式化
     * param id 视频id //上传进度条的id好  多个进度条同
     */
    @RequestMapping(value="upload_video_1",method= RequestMethod.POST)
    public Result uploadVideo1(@RequestParam(value = "id",defaultValue = "0",required = false) int id, @RequestParam("file") MultipartFile file,
                                   Map<String, Object> map,HttpServletRequest request){
        User user = (User) map.get("current_user");
        //aliyun上传进度条接口
        AliyunOss.PutObjectProgressListener progress = new AliyunOss.PutObjectProgressListener(request.getSession());
        progress.totalBytes = file.getSize();
        progress.id = id;

        String res =  AliyunOss.upload_video("video/"+user.getUsername(),file,progress);

        if(res.equals("error")){
            return  new Result(ResultCode.System_ERROR,"上传视频至AliyunOss失败");
        }

        return  new Result(ResultCode.SUCCESS,res);
    }
    /**
     * 获取实时长传进度
     * @param request
     * @return
     */
    @RequestMapping ("upload_video_percent")
    public int getUploadPercent(HttpServletRequest request,@RequestParam(value = "id",defaultValue = "0",required = false) int id){
        HttpSession session = request.getSession();
        Integer p = (Integer)session.getAttribute("upload_percent_"+id);
        int percent = p == null?0:p;
        if(percent>=100){
            session.setAttribute("upload_percent_"+id,0);
        }

        return percent;
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
