package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //标注这个类是一个springboot的应用
public class SpringbootApplication {

    public static void main(String[] args) {
        //将SpringBoot应用启动
        /*
        * 1.判断是否是web项目
        * 2.查找并加载所有可用初始化器，设置到initializer属性中
        * 3.找出所有的应用程序监听器，设置到listener监听器中
        * 4.
        * */
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
