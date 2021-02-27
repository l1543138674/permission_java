package com.acsk.demo.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置文件类
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    /**
     * 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("注册拦截器");
        InterceptorRegistration registration = registry.addInterceptor(new PermissionInterceptor());
        registration.addPathPatterns("/**");//所有路径都被拦截
        registration.excludePathPatterns("/login");

    }
}
