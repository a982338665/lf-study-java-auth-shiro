package pers.li.dao;

import pers.li.entity.User;

import java.util.List;

/**
 * create by lishengbo on 2018-05-31 13:28
 */
public interface UserDao {


    User getPassBYUserName(String userName);

    List<String> getRolesBYUserName(String userName);

    List<String> getPermissionBYUserName(String userName);
}
