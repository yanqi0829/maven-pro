package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

//在templates目录下的所有页面，只能通过controller来跳转
//需要模版引擎 thymeleaf
@Controller
public class TemplateController {

    @RequestMapping("/test")
    public  String test(Model model){
//        model.addAttribute("msg","hello,springboot");
        model.addAttribute("msg","<h1>hello,springboot</h1>");
        model.addAttribute("users", Arrays.asList("y1","y2"));
        return "test";
    }
}
