package zyrs.xyz.obadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ObAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObAdminApplication.class, args);
    }
}
