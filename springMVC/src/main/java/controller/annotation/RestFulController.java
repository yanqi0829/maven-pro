package controller.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RestFulController {
    @RequestMapping("/add1")
    //http://localhost:8088/springMVC_war_exploded/add1?a=1&b=2
    public String test1(int a, int b, Model model) {
        int res = a + b;
        model.addAttribute("msg", "结果为" + res);
        return "test";
    }

    @RequestMapping(name = "/add/{a}/{b}", method = RequestMethod.GET)
    //RestFul:  http://localhost:8088/springMVC_war_exploded/add/1/2
    public String test2(@PathVariable int a, @PathVariable int b, Model model) {
        int res = a + b;
        model.addAttribute("msg", "结果为" + res);
        return "test";
    }
}
