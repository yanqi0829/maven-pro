package controller.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //代表这个类会被spring接管，该类中的方法的返回值如果是String，并且子啊具体页面可跳转，会被视图解析器解析
public class MyController {
    @RequestMapping("/hello1")
    public String hello(Model model) {
        model.addAttribute("msg", "注解形式");
        return "test";
    }

    //springmvc-servlet“没有配置视图解析器时”，底层用的servlet的转发，重定向
    @RequestMapping("/test1")
    public String test1() {
        //转发
        return "/index.jsp";
    }

    @RequestMapping("/test2")
    public String test2() {
        //重定向
        return "redirect:/index.jsp";
    }
}
