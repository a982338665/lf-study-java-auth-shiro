package pers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.li.service.UserService;

/**
 * create by lishengbo on 2018-06-01 10:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class testservice {

    @Autowired
    private UserService userService;

    @Test
    public void reduceNumber() throws Exception {
        String s= userService.test();
        System.out.println(s);
    }
}
