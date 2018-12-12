package zyrs.xyz.obadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class WxAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxAdminApplication.class, args);
    }
}
