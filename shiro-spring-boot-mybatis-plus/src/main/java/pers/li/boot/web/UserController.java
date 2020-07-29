package pers.li.boot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.li.boot.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    public Object getUser(Integer id) {
        return userService.getUserById(id);
    }

    @RequestMapping("/add")
    public Object add(String name) {
        System.out.println("add");
        userService.insertUser(name, "SEX");
        return "SUCCESS";
    }
}
