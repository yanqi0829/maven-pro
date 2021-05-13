package com.example.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 使用自定义类扩展SpringMVC
 * 详见官网7.1.1. Spring MVC Auto-configuration
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    //视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/nihao").setViewName("test");  http://localhost:8082/nihao 至 test页
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }
    @Bean//自定义国际化生效
    public LocaleResolver localeReslver(){
        return  new MyLocaleResolver();
    }
}
