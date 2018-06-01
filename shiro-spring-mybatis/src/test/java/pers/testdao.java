package pers;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.li.dao.UserBeanMapper;
import pers.li.entity.UserBean;

import javax.annotation.Resource;
import java.util.Date;

/**
 * create by lishengbo on 2017-12-28 14:56
 * 配置Spring junit整合，junit启动时要加载spring ioc容器
 * spring-test、junit依赖
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class testdao {

    /**
     *注入Dao实现类依赖
     */
    @Autowired
    private UserBeanMapper userDao;

    @Test
    public void reduceNumber() throws Exception {
        UserBean userBean = userDao.selectByPrimaryKey(1L);
        System.out.println(userBean.getNickname());
    }
}
