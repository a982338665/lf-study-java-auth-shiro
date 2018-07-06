package pers.li.shirospringboot.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import pers.li.shirospringboot.domain.User;
import pers.li.shirospringboot.service.UserService;

/**
 * create by lishengbo on 2018-05-31 09:50
 *
 * 自定义 RealM 实现
 */
@SuppressWarnings("all")
public class UserRealm extends AuthorizingRealm {


    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权+++++++++++++++++++++++++=");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //添加授权字符串--要与ShiroConfig中的对应
        //simpleAuthorizationInfo.addStringPermission("user:add");
        //改为数据库获取：
        //1.获取主体Subject
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        User user=(User)principal;
        //模拟查询--不新写sql了：根据id获取授权资源信息
        //添加授权字符串
        simpleAuthorizationInfo.addStringPermission(user.getPerm());



        return simpleAuthorizationInfo;

    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("认证+++++");
        //仿数据库
        /*String name="1";
        String pass="1";*/

        //转换为登录的token
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        //验证用户名
        User byname = userService.findByname(token.getUsername());
        if(byname==null){
            return null;
        }
       /* if(!token.getUsername().equals(name)){
            return null;
        }*/
        //验证密码
        //三个参数的意思：
        // 第一个-返回给login()方法的一些数据
        // 第二个-数据库密码
        // 第三个-该realm的名称--可为""

        //byname可以再subject中取出来
        return new SimpleAuthenticationInfo(byname,byname.getPass(),"");

    }

}
