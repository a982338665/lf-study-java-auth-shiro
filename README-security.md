# Auth-Shiro -权限控制框架


    1.shiro 架构 ，组件概念
    2.shiro认证，授权过程
    3.自定义Realm，Filter
    4.shiro的session-分布式session
    5.shiro的缓存管理
    6.集成spring

shiro简介：
    
    1.apache强大安全的开源安全框架
    2.认证，授权，企业会话管理，安全加密，缓存管理等

Shiro与Spring Security比较：
    
    1.Shiro简单灵活
    2.Shiro可脱离Spring
    3.权限控制粒度粗
    
Shiro整体架构：
    
    1.Web-mvc--操作用户
    2.shiro核心：
        1.Security Manager  --核心
        ·Authenticator      --认证器：登入登出
            Realms--
        ·Authorizer         --授权器：主体权限
            Realms--
        ·Session Manager    --自定义session容器
            session DAO --
        ·Cache Manager
        2.cryptography      --加密的
    3.底层数据操作：。。。
    
Shiro认证：
    
    1.创建Securitymanager-->主动提交认证-->
    2.Securitymanager认证-->Authenticator认证-->
    3.Realm验证

iniRealm/JdbcRealm/自定义Realm：-- 

Shiro加密：md5 / md5+salt

    1.shiro散列配置：
        ·HashedCredentialsMatcher
        ·自定义Realm中使用散列
        ·盐的使用 --更加安全

授权：
    
    1.编码方式
    2.注解方式:
        --依赖：aspectjweaver
        --配置：spring.xml
        --controller添加:@RequiresRoles("admin")

Shiro过滤器：
    
    1.内置：
        -anon       --不需认证可直接访问
        -authBasic  --httpbasic
        -authc      --认证后可访问
        -user       --需要当前存在用户才可访问
        -logout     --退出
        ====
        授权相关过滤器：
        -perms[...] --具备相关权限可访问 
        -roles[...] --具备相关角色可访问
        -ssl        --安全协议：https
        -port[...]  --要求端口：...
    2.自定义过滤器：
        -实现后需要注入spring.xml
        
shiro会话管理：

    1.SessionManager，SessionDao
    2.Redis实现session共享
    3.Redis实现session共享会出现的问题
    
Shiro的缓存管理：

    0.主要用来缓存角色信息和权限信息，直接从缓存取，提高服务器性能
    1.CacheManager Cache
    2.Redis实现CacheManager
    
Shiro的自动登录：--

**SpringBoot+Shiro**

    1.SpringBoot项目中启动类必须在代码的父级目录，平级目录下会影响扫描controller
    2.SpringBoot项目存放页面的固定位置：src\main\resources\templates
    3.org.xml.sax.SAXParseException: 元素类型 "meta" 必须由匹配的结束标记 "</meta>" 终止:
        错误原因：在thymeleaf3.0以前对页面标签的语法较严格，有开始标签，必要有结束标签
        版本升级：修改thymeleaf版本--pom.xml--properties属性
    4.Shiro的核心Api--
    5.编写shiro配置类:--包含realm
    6.使用Shiro内置过滤器实现页面拦截(需要使用controller进行页面跳转)
    7.整合mybatis:
        `添加依赖
        `application.properties文件创建
    8.实现用户授权
    9.数据库动态授权
    10.页面展示整合-shiro标签:
        1.添加拓展依赖
        2.ShiroConfig中ShiroDialect的配置
