package pers.li.shirospringboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.li.shirospringboot.domain.User;
import pers.li.shirospringboot.mapper.UserMapper;
import pers.li.shirospringboot.service.UserService;

/**
 * create by lishengbo on 2018-07-06 17:47
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByname(String name) {
        return userMapper.findByname(name);
    }
}
