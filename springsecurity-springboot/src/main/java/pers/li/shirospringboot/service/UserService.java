package pers.li.shirospringboot.service;

import pers.li.shirospringboot.domain.User;

/**
 * create by lishengbo on 2018-07-06 17:47
 */
public interface UserService {

    public User findByname(String name);
}
