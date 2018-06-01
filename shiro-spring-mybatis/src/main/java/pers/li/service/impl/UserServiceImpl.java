package pers.li.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.li.dao.UserBeanMapper;
import pers.li.service.UserService;

/**
 * create by lishengbo on 2018-06-01 10:37
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserBeanMapper userBeanMapper;

    public String test() {

        return userBeanMapper.selectByPrimaryKey(1L).getNickname();
    }
}
