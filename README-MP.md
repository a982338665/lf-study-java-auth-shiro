# mybatis+shiro+springboot

## 1. 目录
    
* 01-SpringBoot介绍
* 02-SpringBoot为什么火？
* 03-第一个SpringBoot程序
* 04-SpringBoot访问静态文件
* 05-SpringBoot捕获全局异常
* 06-SpringBoot集成Thymeleaf
* 07-Thymeleaf数据展示
* 08-SpringBoot集成Mybatis
* 09-SpringBoot事务管理
* 10-SpringBoot集成多数据源
* 11-SpringBoot集成MybatisPlus
* 12-MybatisPlus使用介绍
* 13-SpringBoot集成lombok
* 14-SpringBoot集成Shiro
* 15-Shiro登录控制
* 16-Shiro角色权限控制
* 17-SpringBoot全局日志管理
* 18-SpringBoot定时任务
* 19-SpringBoot实现异步
* 20-SpringBoot自定义参数
* 21-SpringBoot启动端口访问路径
* 22-yml配置文件+端口占用解决方法
* 23-SpringBoot多环境区分
* 24-SpringBoot打包发布

## 01-SpringBoot介绍
## 02-SpringBoot为什么火？

    Spring Boot的主要优点：
    为所有Spring开发者更快的入门
    开箱即用，提供各种默认配置来简化项目配置
    内嵌式容器简化Web项目
    没有冗余代码生成和XML配置的要求

## 03-第一个SpringBoot程序

    1.在 HelloController 使用main函数启动boot
    2.自建启动类App
    
## 04-SpringBoot访问静态文件
    
      0.查找路径默认依次为：classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/
          即，当有重复命名的html，将会依次查询以上路径，取到则返回
          例如：访问-http://localhost:8080/index.html（index.html可不写）  Hello /META-INF/resources/index
      1.classpath:/META-INF/resources/：
          http://localhost:8080/index-info.html
          Hello META-INF/resources/*
      2.classpath:/resources/：
          http://localhost:8080/index-resources.html
          Hello /resources/index-resources
      3.classpath:/static/：一般用于存放静态资源(css,js,image等)
          http://localhost:8080/index-static.html
          Hello /static/
      4.classpath:/public/：一般用于存放页面
          http://localhost:8080/index-public.html
          Hello /public/
      注意：使用默认方式templates：用于存放页面，一般是thyleleaf、FreeMaker、Velocity、JSP等 （注意，这个需要引入thymeleaf相关依赖），否则路径不生效
            springboot访问模板类静态资源：直接放在resources/templates目录下即可访问

       访问： http://localhost:8080/1.png
       问题：换成1.jpg好像访问不通
    
## 05-SpringBoot捕获全局异常
    
    GlobalExceptionHandler
    
## 06-SpringBoot集成Thymeleaf

    1.JSP会被编译成Servlet，重量级的
    2.前后端分离模板引擎：
        Thymeleaf
        FreeMarker
        Groovy
    3.使用：
        引依赖
        加配置
        加controller
        访问127.0.0.1:8080/index就可以跳转到我们的index.html页面了
    
## 07-Thymeleaf数据展示
    
    1.集成jsp需要去除模板引擎：
        依赖：
            <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            </dependency>
            <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
            </dependency>
         配置文件：
            spring.mvc.view.prefix=/WEB-INF/jsp/
            spring.mvc.view.suffix=.jsp
    
## 08-SpringBoot集成Mybatis
## 09-SpringBoot事务管理
## 10-SpringBoot集成多数据源
## 11-SpringBoot集成MybatisPlus
## 12-MybatisPlus使用介绍
## 13-SpringBoot集成lombok
## 14-SpringBoot集成Shiro
## 15-Shiro登录控制
## 16-Shiro角色权限控制
## 17-SpringBoot全局日志管理
## 18-SpringBoot定时任务
## 19-SpringBoot实现异步
## 20-SpringBoot自定义参数
## 21-SpringBoot启动端口访问路径
## 22-yml配置文件+端口占用解决方法
## 23-SpringBoot多环境区分
## 24-SpringBoot打包发布

