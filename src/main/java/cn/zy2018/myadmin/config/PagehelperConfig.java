package cn.zy2018.myadmin.config;

import com.github.pagehelper.PageHelper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created by Administrator on 2019/2/12.
 */
@Configuration
@EnableCaching
public class PagehelperConfig {
    //配置mybatis的分页插件pageHelper
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        properties.setProperty("dialect","mysql"); //配置mysql数据库的方言
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
