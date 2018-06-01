package pers.li.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * create by lishengbo on 2018-05-31 14:51
 *
 * 自定义filter --- 传多个角色，满足任何一个即可
 *
 * 需要在spring.xml中注入
 *
 * 授权相关：继承-AuthorizationFilter
 * 认证相关：继承-AuthenticatingFilter
 */
public class RolesOrFilter extends AuthorizationFilter {


    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        //获取主体
        Subject subject = getSubject(servletRequest,servletResponse);

        //object o 即为中括号中的角色数组
        String[] roles=(String[])o;
        //对角色不限制
        if(roles==null||roles.length==0){
            return true;
        }

        //传过来的角色列表中有任何一个满足即可
        for (String role:roles
             ) {
            if(subject.hasRole(role)){
                return true;
            }
        }

        return false;

    }
}
