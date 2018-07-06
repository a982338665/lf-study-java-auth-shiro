package pers.li.shirospringboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * create by lishengbo on 2018-07-06 14:17
 *
 *
 *
 */
@Controller
public class UserController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){

        return "hello";
    }
    @RequestMapping("/testhtml")
    public String testhtml(Model m){

        m.addAttribute("hellllo","long");
        //返回到hello.html
        return "hello";
    }
    @RequestMapping("/user/{path}")
    public String forward(@PathVariable("path")String s, Model m){

        return "/user/"+s;
    }



    @PostMapping("/login.do")
    public String login(String name,String password,Model model){
        //使用shiro编写认证操作
        System.out.println("name=="+name);
        //1.获取主体Subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token=new UsernamePasswordToken(name,
                password);
        try{
            //设置记住密码-自动登录-会自动存储cookie
            //token.setRememberMe(user.isRememberMe());
            subject.login(token);
        }catch(UnknownAccountException e){
            model.addAttribute("msg","登录失败：账号不存在"+e.getMessage());
            //页面跳转--走页面
            return "/user/login";
        }catch(IncorrectCredentialsException e){
            model.addAttribute("msg","登录失败：密码错误"+e.getMessage());
            return "/user/login";
        }
        //登陆成功，跳转主页--重定向走接口
        return "redirect:testhtml";
    }
}
