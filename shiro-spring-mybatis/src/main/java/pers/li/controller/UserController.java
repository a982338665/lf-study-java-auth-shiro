package pers.li.controller;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.li.utils.PropertyUtil;

/**
 * create by lishengbo on 2018-05-31 11:35
 */
@Controller
public class UserController {

    /**
     * 获取盐值密码
     * @return
     */
    @RequestMapping(value = "/a/{psd}",
            method = RequestMethod.GET,
            produces = "application/json;charset=utf8"
    )
    @ResponseBody
    public String subLogin(@PathVariable("psd") String psd){
        return new Md5Hash(psd, PropertyUtil.getProperty("salt")).toString();
    }

    public static void main(String[] args) {
        System.out.println(PropertyUtil.getProperty("on"));
    }


  /*  *//**
     * produces--表示返回值为json格式且为utf-8
     * @param user
     * @return
     *//*
    @RequestMapping(value = "/subLogin",
            method = RequestMethod.POST,
            produces = "application/json;charset=utf8"
    )
    @ResponseBody
    public String subLogin(User user){
        //获取主体
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),
                user.getPassword());
        System.out.println(user.isRememberMe());
        try{
            //设置记住密码-自动登录-会自动存储cookie
            token.setRememberMe(user.isRememberMe());
            subject.login(token);
        }catch(Exception e){

            return e.getMessage();

        }
        if(subject.hasRole("admin")){
            return "有admin权限";
        }else{
            return "无admin权限";
        }
    }


    *//**
     * 此注解表示，当前用户有admin权限才可以访问
     * @return
     *//*
    @RequiresRoles("admin")
//    @RequiresPermissions("")
    @RequestMapping(value = "/testRole",method = RequestMethod.GET)
    @ResponseBody
    public String testRoles(){
        return "test role success";
    }



    //提交
    //++++++++++++++++++++将注解配置加在spring.xml+++++++++++++++++++++++
    *//**
     * @return
     *//*
    @RequestMapping(value = "/testPerms",method = RequestMethod.GET)
    @ResponseBody
    public String testPerms(){
        return "test testPerms success";
    }
    *//**
     * @return
     *//*
    @RequestMapping(value = "/testPerms1",method = RequestMethod.GET)
    @ResponseBody
    public String testPerms1(){
        return "test testPerms1 success";
    }
    *//**
     * @return
     *//*
    @RequestMapping(value = "/role1",method = RequestMethod.GET)
    @ResponseBody
    public String role1(){
        return "test role1 success";
    }
    *//**
     * @return
     *//*
    @RequestMapping(value = "/role2",method = RequestMethod.GET)
    @ResponseBody
    public String role2(){
        return "test role2 success";
    }
    *//**
     * @return
     *//*
    @RequestMapping(value = "/role3",method = RequestMethod.GET)
    @ResponseBody
    public String role3(){
        return "test role3 success";
    }*/

}
