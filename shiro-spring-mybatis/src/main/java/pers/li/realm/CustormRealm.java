package pers.li.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import pers.li.dao.UserBeanMapper;
import pers.li.entity.UserBean;
import pers.li.utils.PropertyUtil;

import javax.annotation.Resource;
import java.util.*;

/**
 * create by lishengbo on 2018-05-31 09:50
 *
 * 自定义 RealM 实现
 */
@SuppressWarnings("all")
public class CustormRealm extends AuthorizingRealm {


    @Resource
    private UserBeanMapper userDao;

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
        Map<String, Set<String>> perss=getPermissionBYUserName(roles);
        Collection<Set<String>> values = perss.values();
        Set<String> set=new HashSet<String>();
        for (Set<String> s:values){
            set.addAll(s);
        }
        //4.创建返回信息
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(set);
        return simpleAuthorizationInfo;
    }

    /**
     * 获取权限信息
     * @param userName
     * @return
     */
    private  Map<String, Set<String>> getPermissionBYUserName(Set<String> roles) {
        Map<String, Set<String>> map=new HashMap<String, Set<String>>();
        for (String role:roles
             ) {
            List<String> pers =userDao.getPermissionBYUser(role);
            Set<String> strings = new HashSet<String>(pers);
            map.put(role,strings);
        }

        return map;
    }

    /**
     *  获取角色信息
     * @param userName
     * @return
     */
    private Set<String> getRolesBYUserName(String userName) {
        System.out.println("从数据库中获取授权数据---");
        List<String> roles =userDao.getRolesBYUser(userName);
        Set<String> strings = new HashSet<String>(roles);
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
        //2.通过用户名到数据库/缓存中获取角色信息--模拟数据库查询
        Set<String> roles=getRolesBYUserName(userName);
        //3.权限
        Map<String, Set<String>> perss=getPermissionBYUserName(roles);
        //创建返回信息
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(perss,passWord,"customRealm");

        //盐的设置 TODO
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(PropertyUtil.getProperty("salt")));

        return authenticationInfo;
    }
    /**
     * 数据库查询--认证信息
     * @param userName
     * @return
     */
    private String getPassBYUserName(String userName) {
        UserBean user=userDao.getPassBYUserName(userName);
        if(user!=null){
            return user.getPswd();
        }

        return null;
    }

    public static void main(String[] args) {

        //md5加密计算--无盐
        System.out.println(new Md5Hash("123456"));
        //md5+盐
        System.out.println(new Md5Hash("123456","Mark"));


    }
}
