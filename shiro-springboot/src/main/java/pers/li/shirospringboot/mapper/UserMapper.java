package pers.li.shirospringboot.mapper;

import pers.li.shirospringboot.domain.User;

/**
 * create by lishengbo on 2018-07-06 17:38
 */
public interface UserMapper {

    public User findByname(String name);
}
