package controller.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//乱码问题,配置过滤器解决
@Controller
public class EncodingController {

    //http://localhost:8088/springMVC_war_exploded/form.jsp
    @RequestMapping(name = "/hello", method = RequestMethod.POST)
    public String hello(String name, Model model) {
        System.out.println(name);
        model.addAttribute("msg", name);
        return "test";
    }
}
