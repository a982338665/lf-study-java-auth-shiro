package pers.li.shirospringboot.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * create by lishengbo on 2018-07-06 15:00
 *
 * shiro的配置类
 */
/*申明为配置*/
@Configuration
public class ShiroConfig {


    //1.创建ShiroFilterFactoryBean
    @Bean//此bean不加，则过滤器无作用
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("manager")DefaultWebSecurityManager manager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(manager);
        //添加Shiro内置过滤器：可实现权限相关拦截器--
        //拦截规则查看readme文件
        //
        Map<String,String> map=new LinkedHashMap<>();
        /*map.put("/user/add","authc");
        map.put("/user/update","authc");*/
        //user路径下的所有资源都使用以下过滤器
        //map.put("/user/*","authc");
       /* map.put("/user/add","authc");
        map.put("/user/update","authc");
        map.put("/testhtml","anon");*/
        map.put("/login.do","anon");

        //添加授权过滤器:授权拦截后会跳转到未授权页面
        map.put("/user/add","perms[user:add]");
        map.put("/user/update","perms[user:update]");

        map.put("/*","authc");

        //修改认证页面--登录页面走接口
        shiroFilterFactoryBean.setLoginUrl("/user/login");
        //设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/user/unAuthor");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;

    }



    //2.创建DefaultWebSecurityManager
    //@Qualifier("userRealm")-- 找到Spring容器中的UserRealm
    @Bean(name = "manager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm realm){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        //设置realm
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;

    }




    //3.创建自定义Realm
    //申明为bean注入Spring容器
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

    /**
     * 配置ShiroDialect--整合shiro标签
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return  new ShiroDialect();
    }
}
