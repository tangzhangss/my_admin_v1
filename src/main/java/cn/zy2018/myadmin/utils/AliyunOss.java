package cn.zy2018.myadmin.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.InputStream;

/**
 * 阿里云 oss文件操作
 *
 * Created by Administrator on 2018/11/21.
 */
public class AliyunOss {

    //EndPoint（地域节点）
    private static String endpoint = "oss-cn-beijing.aliyuncs.com";

    //oss用户_非主用户
    private static String accessKeyID = "LTAIyEbHDEEdSrH5";
    //oss用户_非主用户
    private static String accessKeySecret = "C9fdLHsaSp6UJAkwsLJ3EPdZIEnE7Z";

    //图片样式
    private static String pictureStyle = "my1admin-picture-style";

    //桶名
    private static String bucket = "my1admin";

    //域名...地址
    private static String urlStr = "https://" + bucket + "." + endpoint;


    public AliyunOss(String ossEndpoint,String ossAccessKeyID,String ossAccessKeySecret,String bucket){

        this.bucket = bucket;
        this.endpoint = ossEndpoint;
        this.accessKeyID = ossAccessKeyID;
        this.accessKeySecret = ossAccessKeySecret;
        this.urlStr = "https://" + this.bucket + "." + this.endpoint;
    }

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
            return "error";
        } finally {
            ossClient.shutdown();
        }
    }

    /**
     * 上传文件
     * @param filepath  文件地址  前后面不带/
     * @param inputStream 数据流
     * @param filename  文件名 至少 有后缀 如 .jpg
     * @return
     */
    public static String uploadFile(String filepath, InputStream inputStream, String filename) {
        // 创建OSSClient实例。
        OSSClient ossClient = null;
        try {
            ossClient = new OSSClient(endpoint, accessKeyID, accessKeySecret);
            //返回服务器地址
            String objectUrl = filepath + "/" + System.currentTimeMillis() + filename;

            ossClient.putObject(bucket, objectUrl,inputStream);

            return urlStr + "/" + objectUrl;

        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }

        return "error";
    }

    /**
     * 上传文件1
     * @param filePath  文件地址含文件名  前后面不带/
     * @param inputStream 数据流
     * @return
     */
    public static String uploadFile1(String filePath, InputStream inputStream) {
        // 创建OSSClient实例。
        OSSClient ossClient = null;
        try {
            ossClient = new OSSClient(endpoint, accessKeyID, accessKeySecret);
            //返回服务器地址
            String objectUrl = filePath;

            ossClient.putObject(bucket, objectUrl,inputStream);

            return urlStr + "/" + objectUrl;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }

        return "error";
    }

    /**
     * 上传视频
     * @param filepath 文件地址
     * @param file 文件对象
     * @param  progress 进度条对像
     * @return
     */
    public static String upload_video(String filepath, MultipartFile file,PutObjectProgressListener progress) {
        // 创建OSSClient实例。
        OSSClient ossClient = null;
        try {
            ossClient = new OSSClient(endpoint, accessKeyID, accessKeySecret);

            //返回服务器地址
            String objectUrl = filepath + "/" + System.currentTimeMillis() + "_" + file.getOriginalFilename();

            ossClient.putObject(new PutObjectRequest(bucket, objectUrl, file.getInputStream()).
                    <PutObjectRequest>withProgressListener(progress));

            return urlStr + "/" + objectUrl;

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


    /**
     * 上传进度条
     */
    public static class PutObjectProgressListener implements ProgressListener {

        private HttpSession session;
        private long bytesWritten = 0;
        public  long totalBytes = -1;
        private boolean succeed = false;
        private int percent = 0;
        public int id;//上传进度条的id好  多个进度条同时
        //构造方法中加入session
        public PutObjectProgressListener() {
        }
        public PutObjectProgressListener(HttpSession mSession) {
            this.session = mSession;
            session.setAttribute("upload_percent_"+id, percent);
        }
        @Override
        public void progressChanged(ProgressEvent progressEvent) {
            long bytes = progressEvent.getBytes();
            ProgressEventType eventType = progressEvent.getEventType();
            switch (eventType) {
                case TRANSFER_STARTED_EVENT:
                    System.out.println("Start to upload......");
                    break;
                case REQUEST_CONTENT_LENGTH_EVENT:
                    this.totalBytes = bytes;
                    System.out.println(this.totalBytes + " bytes in total will be uploaded to OSS");
                    break;
                case REQUEST_BYTE_TRANSFER_EVENT:
                    this.bytesWritten += bytes;
                    if (this.totalBytes != -1) {
                        int percent = (int)(this.bytesWritten * 100.0 / this.totalBytes);
                        //将进度percent放入session中
                        session.setAttribute("upload_percent_"+id, percent);

                        //System.out.println(bytes + " bytes have been written at this time, upload progress: " +
                         //       percent + "%(" + this.bytesWritten + "/" + this.totalBytes + ")");
                    } else {
                        //System.out.println(bytes + " bytes have been written at this time, upload ratio: unknown" +
                         //       "(" + this.bytesWritten + "/...)");
                    }
                    break;
                case TRANSFER_COMPLETED_EVENT:
                    this.succeed = true;
                    System.out.println("Succeed to upload, " + this.bytesWritten + " bytes have been transferred in total");
                    break;
                case TRANSFER_FAILED_EVENT:
                    System.out.println("Failed to upload, " + this.bytesWritten + " bytes have been transferred");
                    break;
                default:
                    break;
            }
        }
        public boolean isSucceed() {
            return succeed;
        }
    }
}


