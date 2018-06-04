package pers.li.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pers.li.common.AuthCode;
import pers.li.common.AuthResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * create by lishengbo on 2018-06-01 11:43
 */
@Controller
@RequestMapping("/users")
@SuppressWarnings("ALL")
public class UserWeb {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * produces--表示返回值为json格式且为utf-8
     *
     * @return
     */
    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            produces = "application/json;charset=utf8"
    )
//  @ResponseBody转对象时，要导jar包：jackson-databind
    @ResponseBody
    public AuthResult subLogin(HttpServletRequest request) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean rememberMe = request.getParameter("rememberMe") == null ? false : true;
        logger.debug("用户：" + username + "使用密码-" + password + "登录|是否记住密码：" + rememberMe);

        //验证登录
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username,
                password);
        try {
            //设置记住密码-自动登录-会自动存储cookie
            token.setRememberMe(rememberMe);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return new AuthResult(AuthCode.Auth_Fail_UserName);
        } catch (IncorrectCredentialsException e) {

            return new AuthResult(AuthCode.Auth_Fail_PassWord);
        } catch (Exception e) {

            return new AuthResult(AuthCode.Auth_Fail_Login);
        }
        return new AuthResult(AuthCode.Auth_Successs);
    }


    /**
     * 获取角色菜单
     * produces--表示返回值为json格式且为utf-8
     *
     * @return
     */
    @RequestMapping(value = "/getRoleMenu",
            method = RequestMethod.GET,
            produces = "application/json;charset=utf8"
    )
//  @ResponseBody转对象时，要导jar包：jackson-databind
    @ResponseBody
    public /*Map<String, Set<String>>*/String getRoleMenu() {

        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        Object attribute = session.getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY");
        String s = attribute.toString();
        System.out.println("------------"+attribute.toString()+"------------");


       /* //获取当前用户的用户名---
        Map<String, Set<String>> perss=(Map<String, Set<String>>)attribute;
        Set<String> keySet = perss.keySet();
        for (String key:keySet
             ) {
            Set<String> strings = perss.get(key);
            for (String value:strings
                 ) {
                System.out.print (key+"-"+value);
            }
            System.out.println();
        }*/

      /*  subject.hasRole("");
        System.err.println("ppp"+principals.getPrimaryPrincipal());
        PrincipalCollection previousPrincipals = subject.getPreviousPrincipals();
        Object primaryPrincipal = previousPrincipals.getPrimaryPrincipal();
        System.err.println("ttt"+previousPrincipals);*/
        return "122";
    }


}
