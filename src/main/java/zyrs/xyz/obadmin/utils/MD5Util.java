package zyrs.xyz.obadmin.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.util.DigestUtils;

public class MD5Util {
    public static String encryptPassword(String password){
        String algorithmName ="MD5";
        Object salt = null;
        int hashIterations = 2;
        SimpleHash s= new SimpleHash(algorithmName, password, salt, hashIterations);

        return s.toString();
    }

    public static String MD5(String str){
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

}
