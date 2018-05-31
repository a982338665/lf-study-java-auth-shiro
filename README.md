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

