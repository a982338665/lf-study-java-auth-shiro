package pers.li.boot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ，在类上注解Controller而不是RestController，RestController代表是返回JSON字符串给前台，而Controller注解是返回页面
 */
@Controller
public class CommonController {
    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("name", "hello");
        Map<String,String> map = new HashMap<String,String>();
        map.put("userName","皇夜");
        map.put("email","皇夜@qq.com");
        Map<String,String> map2 = new HashMap<String,String>();
        map2.put("userName","皇夜2");
        map2.put("email","皇夜2@qq.com");
        model.addAttribute("userlist", Arrays.asList(map,map2));
        return "index";
    }
}
