package controller.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ParamController {
    //接收
    //1.提交的域名称和处理方法的参数名一致
    //http://localhost:8088/springMVC_war_exploded/param1?name=123
    @RequestMapping("/param1")
    public String param1(String name, Model model) {
        System.out.println(name);

        model.addAttribute("msg", name);

        return "test";
    }
    //2.提交的域名称和处理方法的参数名不一致
    //http://localhost:8088/springMVC_war_exploded/param1?request=000
    @RequestMapping("/param2")
    public String param2(@RequestParam("request") String name, Model model) {
        System.out.println(name);

        model.addAttribute("msg", name);

        return "test";
    }

    //3.提交User 对象  ,表单域和对象的属性名一致，不一致 null
    //http://localhost:8088/springMVC_war_exploded/param3?name=11&age=11
    @RequestMapping("/param3")
    public String param3(User user) {
        System.out.println(user);
        return "test";
    }

    //回显
}
