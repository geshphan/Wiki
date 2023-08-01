package com.yemenshi.geshphan.configuration;

import com.yemenshi.geshphan.interceptors.MagicInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MagicInterceptor())
                .excludePathPatterns("/css/**", "/static/js/**", "/images/**");
    }
}
