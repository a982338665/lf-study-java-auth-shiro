import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * create by lishengbo on 2018-05-30 17:26
 *
 *
 *
 */
public class test_iniRealm {



    @Test
    public void testAuth(){
        //构建IniRealm
        IniRealm iniRealm=new IniRealm("classpath:user.ini");

        //1.构建Security Manager环境
        DefaultSecurityManager manager=new DefaultSecurityManager();
        manager.setRealm(iniRealm);

        //2.主体提交认证请求
        //设置环境
        SecurityUtils.setSecurityManager(manager);
        //获取主体
        Subject subject = SecurityUtils.getSubject();

        //3.提交认证，登录--提交正确用户名密码
        UsernamePasswordToken token=new UsernamePasswordToken("Mark","123456");
        subject.login(token);
        System.out.println("登录isAuthenticated结果--->"+subject.isAuthenticated());

        //权限控制
        subject.checkRole("admin");

        subject.checkPermission("user:delete");
        subject.checkPermission("user:update");
        subject.checkPermissions("user:update","user:delete");

    }
}
