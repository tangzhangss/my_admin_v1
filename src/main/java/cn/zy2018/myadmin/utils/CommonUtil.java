package cn.zy2018.myadmin.utils;

import com.swetake.util.Qrcode;
import org.apache.commons.lang.StringUtils;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by Administrator on 2019/11/21.
 */
public class CommonUtil {

    public static void printLog(String...str){
        for (String s : str)
            System.out.print(s);
        System.out.println();
    }
    public static String getUrlQueryByHttpServletRequest(HttpServletRequest request){

        String url = request.getRequestURL().toString();
        String query =request.getQueryString();
        if(StringUtils.isNotBlank(query)){
            query = "?"+query;
        }else{
            query="";
        }
        return url + query;
    }

    /**
     * 绘制二维码
     * @param content  二维码内容 --html
     * @param version 版本号避免又是数据过大生成失败,将版本号弄成动态的避免生成的图片大小不合适 可设置成12
     * @param color 二维码的颜色，前景色 16进制 #123456
     * @param fileInputStream   二维码的logo 图片流
     * @param filepath 上传文件路径
     *@param filename 上传文件名 如 _.jpg
     * @return 二维码图片的网络地址
     */
    public static String createQrcode(String content, String color, int version,
                                      InputStream fileInputStream, String filepath,String filename){
        //实例化Qrcode对象
        Qrcode qrcode = new Qrcode();
        //设置容错率
         /*二维码的纠错级别(排错率)，共有四级：可选L(7%)、M(15%)、Q(25%)、H(30%)(最高H)。
			 	纠错信息同样存储在二维码中，纠错级别越高，纠错信息占用的空间越多，那么能存储的有用信息就越少,对二维码清晰度的要求越小  */
        qrcode.setQrcodeErrorCorrect('L');
        //设置版本号
        /*
			 二维码的版本号：也象征着二维码的信息容量；二维码可以看成一个黑白方格矩阵，版本不同，矩阵长宽方向方格的总数量分别不同。
			 1-40总共40个版本，版本1为21*21矩阵，版本每增1，二维码的两个边长都增4；
			版本2 为25x25模块，最高版本为是40，是177*177的矩阵；
			 */
        qrcode.setQrcodeVersion(version);

        //设置画板，img高宽--利用公式计算-版本号匹配的高宽
        int width = 67+12*(version-1);//version是版本号
        int height = 67+12*(version-1);//这是一个公式，自己随意定义的话会太大，比如300，一张图会有空白页，二维码就在左上角了

        //创建画板
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //在画板上创建画笔
        Graphics2D gs = img.createGraphics();
        //设置背景。前景色。
        gs.setColor(new Color(Integer.valueOf(color.substring(1),16)));
        gs.setBackground(Color.white);
        //设置画笔的会话区域--和画板不同。画板只需要大小而画笔需要位置
        gs.clearRect(0, 0, width, height);
        //设置抗锯齿
        gs.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //通过qrcode封装的方法将contentz转化为boolean类型的二位数组，以真和假的方式在画板上画正方形，像素点形成图像
        try {
            byte[] code = content.getBytes("utf-8");
            boolean[][] codes = qrcode.calQrcode(code);
            //遍历codes填充
            for(int i=0;i< codes.length;i++){
                for(int j = 0; j<codes.length;j++){
                    if(codes[i][j]){
                        gs.fillRect(j*3+2, i*3+2, 3, 3);//2是间距
                    }
                }
            }
            if(null!=fileInputStream){
                //插入logo图片到二维码中
                BufferedImage image = ImageIO.read(fileInputStream);
                gs.drawImage(image.getScaledInstance(width/4,
                        height/4, Image.SCALE_SMOOTH), (width-width/4)/2,(height - height/4)/2,null);
            }

            gs.dispose();//销毁画笔

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(img, "png", os);//必须输出png 输出jpg图片非常模糊
            InputStream is = new ByteArrayInputStream(os.toByteArray());

            return AliyunOss.uploadFile(filepath,is,filename);

        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("版本号太小，下一个版本号"+ (version+1));
            //如果是由于版本号太小，储存不够则--版本号+1继续生成-知道够为止
            return createQrcode( content, color, version+1, fileInputStream, filepath,filename);
        }catch(Exception e) {
            e.printStackTrace();
            return  e.getMessage();
        }
    }

    /**
     * @Description: 获取视频截图
     * @param  filePath 路径
     * @param  targetPath 目标路径
     */
    public static String getScreenshot(String filePath,String targetPath) throws Exception{

        FFmpegFrameGrabber ff = FFmpegFrameGrabber.createDefault(filePath);
        ff.start();

        int lenght = ff.getLengthInFrames();
        int i = 0;
        org.bytedeco.javacv.Frame f = null;
        while (i < lenght) {
            // 过滤前5帧，避免出现全黑的图片，依自己情况而定
            f = ff.grabFrame();
            if ((i > 5) && (f.image != null)) {
                break;
            }
            i++;
        }
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bi = converter.getBufferedImage(f);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bi, "png", os);
        InputStream input = new ByteArrayInputStream(os.toByteArray());
        String addr =  AliyunOss.uploadFile1(targetPath,input);
        System.out.println("封面地址:"+addr);
        return addr;
    }
}
