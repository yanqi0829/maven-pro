package controller.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
    @RequestMapping("/hello1")
    public String hello(Model model) {
        model.addAttribute("msg", "注解形式");
        return "test";
    }
}
