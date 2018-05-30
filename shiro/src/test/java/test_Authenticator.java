import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * create by lishengbo on 2018-05-30 16:41
 * 认证测试-----
 */
public class test_Authenticator {


    SimpleAccountRealm realm=new SimpleAccountRealm();

    /**
     * 测试之前添加用户信息--管理账号信息
     */
    @Before
    public void addUser(){
        realm.addAccount("Mark","123456");
    }


    @Test
    public void testAuth(){

        //1.构建Security Manager环境
        DefaultSecurityManager manager=new DefaultSecurityManager();
        manager.setRealm(realm);

        //2.主体提交认证请求
        //设置环境
        SecurityUtils.setSecurityManager(manager);
        //获取主体
        Subject subject = SecurityUtils.getSubject();

        //3.提交认证，登录--提交正确用户名密码
        UsernamePasswordToken token=new UsernamePasswordToken("Mark","123456");
        subject.login(token);
        System.out.println("登录isAuthenticated结果--->"+subject.isAuthenticated());
        subject.logout();
        System.out.println("登出isAuthenticated结果--->"+subject.isAuthenticated());

        //提交-错误账号：报错：UnknownAccountException
//        UsernamePasswordToken token2=new UsernamePasswordToken("Mark1","123456");
//        subject.login(token2);


        //提交-错误密码：报错：IncorrectCredentialsException
//        UsernamePasswordToken token3=new UsernamePasswordToken("Mark1","1234567");
//        subject.login(token3);

    }



}
