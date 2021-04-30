package com.example.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //屏蔽解析器 返回字符串
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello,world";
    }
}
