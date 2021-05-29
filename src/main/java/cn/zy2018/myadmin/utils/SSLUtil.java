package cn.zy2018.myadmin.utils;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

/**
 * Created by Administrator on 2019/8/27.
 */
public class SSLUtil {

    public static SSLContext createSSLContext(String type , String path , String password){
        try{
            KeyStore ks = KeyStore.getInstance(type); /// "JKS"
            InputStream ksInputStream = new FileInputStream(path); /// 证书存放地址
            ks.load(ksInputStream, password.toCharArray());
            //KeyManagerFactory充当基于密钥内容源的密钥管理器的工厂。
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());//getDefaultAlgorithm:获取默认的 KeyManagerFactory 算法名称。
            kmf.init(ks, password.toCharArray());
            //SSLContext的实例表示安全套接字协议的实现，它充当用于安全套接字工厂或 SSLEngine 的工厂。
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), null, null);
            return sslContext;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }
}
