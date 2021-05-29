package cn.zy2018.myadmin;

import cn.zy2018.myadmin.service.api.RedisService;
import net.minidev.json.JSONUtil;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WxAdminApplicationTests {
    @Autowired
    RedisService redisService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void token(){
        redisService.getToken("tangzhangss","10001",30l);
    }

    @Test
    public void base64DEcoder() {
        System.out.println(new String(Base64.decodeBase64("dGFuZ3poYW5nc3NfXzEwMDAx")));
    }

    /**
     * 测试redis
     */
    @Test
    public void redisPressure(){

        for (int j =0;j<1000000;j++){
            String key = System.currentTimeMillis() +"_"+j;
            redisService.set(key,"..........");
            System.out.println(redisService.get(key));
        }

    }

    @Test
    public void hashmap(){
        HashMap<String,Integer> extras = new HashMap();
        System.out.println(extras.get("ass"));
    }
}
