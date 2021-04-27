package json;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {
    //
    @RequestMapping("/j1")
    @ResponseBody  //不会走视图解析器，会直接返回一个字符串
    public String json1() {
        User user = new User("张三", 19, "男");
        String string = JSONObject.toJSONString(user);
        return string;
    }
}
