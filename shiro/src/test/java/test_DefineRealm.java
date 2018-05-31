import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import pers.li.realm.CustormRealm;

/**
 * create by lishengbo on 2018-05-30 17:26
 *
 *
 *
 */
public class test_DefineRealm {

    public static void main(String[] args) {

        //md5加密计算--无盐
        System.out.println(new Md5Hash("123456"));
        //md5+盐
        System.out.println(new Md5Hash("123456","Mark"));


    }


    @Test
    public void testAuth(){
        //构建自定义Realm
        CustormRealm custormRealm = new CustormRealm();

        //1.构建Security Manager环境
        DefaultSecurityManager manager=new DefaultSecurityManager();
        manager.setRealm(custormRealm);




        //设置加密：加密方式及次数================================================
        HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        //自定义realm中设置matcher
        custormRealm.setCredentialsMatcher(matcher);
        //设置加密：加密方式及次数================================================



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
        subject.checkRoles("admin","user");

        subject.checkPermission("user:select");
//        subject.checkPermission("user:update");
//        subject.checkPermissions("user:update","user:delete");

    }


}
