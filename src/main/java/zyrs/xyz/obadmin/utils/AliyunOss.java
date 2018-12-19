package zyrs.xyz.obadmin.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.ExceptionFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 阿里云 oss文件操作
 *
 * Created by Administrator on 2018/11/21.
 */
public class AliyunOss {

    //EndPoint（地域节点）
    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";

    //oss用户_非主用户
    private static String accessKeyID = "LTAIyEbHDEEdSrH5";
    //oss用户_非主用户
    private static String accessKeySecret = "C9fdLHsaSp6UJAkwsLJ3EPdZIEnE7Z";

    //图片样式
    private static String pictureStyle = "ob-picture-style";

    //桶名
    private static String bucket = "ob-admin";

    //域名...地址
    private static String urlStr = endpoint.replace("http://", "http://" + bucket + ".");

    public static String upload_picture(String filepath, MultipartFile file) {
        // 创建OSSClient实例。
        OSSClient ossClient = null;
        try {
            ossClient = new OSSClient(endpoint, accessKeyID, accessKeySecret);

            //返回服务器地址
            String objectUrl = filepath + "/" + System.currentTimeMillis() + "_" + file.getOriginalFilename();

            ossClient.putObject(bucket, objectUrl, file.getInputStream());

            return urlStr + "/" + objectUrl + "?x-oss-process=style/" + pictureStyle;

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        } finally {
            ossClient.shutdown();
        }
    }

    public static String deleteObject(String filepath) {

        // 创建OSSClient实例。
        OSSClient ossClient = null;
        try {
            ossClient = new OSSClient(endpoint, accessKeyID, accessKeySecret);

            // 删除文件。
            ossClient.deleteObject(bucket, filepath);

            return "200";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        } finally {
            ossClient.shutdown();
        }
    }
}


