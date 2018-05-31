package pers.li.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * create by lishengbo on 2018-05-31 09:50
 *
 * 自定义 RealM 实现
 */
public class CustormRealm extends AuthorizingRealm {


    /**
     * 授权
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //1.获取主体传过来的认证信息--用户名
        String userName=(String)principalCollection.getPrimaryPrincipal();
        //2.通过用户名到数据库/缓存中获取角色信息--模拟数据库查询
        Set<String> roles=getRolesBYUserName(userName);
        //3.权限
        Set<String> perss=getPermissionBYUserName(userName);
        //4.创建返回信息
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(perss);
        return simpleAuthorizationInfo;
    }

    /**
     * 获取权限信息
     * @param userName
     * @return
     */
    private Set<String> getPermissionBYUserName(String userName) {
        Set<String> strings = new HashSet<String>();
        strings.add("user:select");
        strings.add("user:delete");
        return strings;
    }

    /**
     *  获取角色信息
     * @param userName
     * @return
     */
    private Set<String> getRolesBYUserName(String userName) {
        Set<String> strings = new HashSet<String>();
        strings.add("admin");
        strings.add("user");
        return strings;

    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.获取主体传过来的认证信息--用户名
        String userName=(String)authenticationToken.getPrincipal();

        //2.通过用户名到数据库中获取凭证--模拟数据库查询
        String passWord=getPassBYUserName(userName);
        if(passWord==null){
            return null;
        }
        //创建返回信息
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName,passWord,"customRealm");

        //盐的设置 TODO
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("Mark"));

        return authenticationInfo;
    }

    /**
     * 数据库查询
     * @param userName
     * @return
     */
    Map<String,String> map=new HashMap<String,String>();
    {
        //md5
//        map.put("Mark","e10adc3949ba59abbe56e057f20f883e");
        //md5+盐
        map.put("Mark","283538989cef48f3d7d8a1c1bdf2008f");

        //设置自定义Realm的名称
        super.setName("customRealm");
    }
    private String getPassBYUserName(String userName) {
        return map.get(userName);
    }
}
