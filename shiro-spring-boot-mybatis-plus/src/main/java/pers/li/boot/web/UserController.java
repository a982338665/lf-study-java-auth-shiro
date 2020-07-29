package pers.li.boot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.li.boot.dao.test1.User1Dao;
import pers.li.boot.dao.test2.User2Dao;
import pers.li.boot.entities.User;
import pers.li.boot.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private User1Dao user1Dao;
    @Autowired
    private User2Dao user2Dao;

    @RequestMapping("/get")
    public Object getUser(Integer id) {
        return userService.getUserById(id);
    }

    @RequestMapping("/get2")
    public String getUser2(Integer id) {
        User user = user1Dao.selectUserById(id);
        User user1 = user2Dao.selectUserById(id);
        User userById = userService.getUserById(id+1);
        return "1:"+user.toString()+"\n"+"2:"+user1.toString()+"\n"+"o:"+userById.toString()+"\n";
    }
    @RequestMapping("/get3")
    public String getUser3(Integer id) {
        User user1 = user2Dao.selectUserById(id);
        return user1.toString();
    }

    @RequestMapping("/add")
    public Object add(String name) {
        System.out.println("add");
        userService.insertUser(name, "SEX");
        return "SUCCESS";
    }
}
