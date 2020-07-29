package pers.li.boot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ，在类上注解Controller而不是RestController，RestController代表是返回JSON字符串给前台，而Controller注解是返回页面
 */
@Controller
public class CommonController {
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
