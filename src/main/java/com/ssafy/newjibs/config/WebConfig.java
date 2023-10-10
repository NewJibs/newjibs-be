package com.ssafy.newjibs.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")  // 정적 리소스에 대한 URL 패턴
                .addResourceLocations("classpath:/static/");  // 정적 리소스가 위치한 디렉터리
    }
}
