package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //标注这个类是一个springboot的应用
public class SpringbootApplication {

    public static void main(String[] args) {
        //将SpringBoot应用启动
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
