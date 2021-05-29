package cn.zy2018.myadmin.config;

import cn.zy2018.myadmin.interceptor.AuthTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(getAuthTokenInterceptor()).addPathPatterns("/token-api/**");
    }

    @Bean
    public AuthTokenInterceptor getAuthTokenInterceptor() {
        return new AuthTokenInterceptor();
    }
}