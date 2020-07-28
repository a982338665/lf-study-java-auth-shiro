package pers.li.boot.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 演示controller
 */
@RestController
public class TestController {

    /**
     * 运行时异常捕获测试
     * @return
     */
    @RequestMapping("/x1")
    public String hello() {
        int x = 10/0;
        return "Hello Majiaxueyuan!";
    }


}
