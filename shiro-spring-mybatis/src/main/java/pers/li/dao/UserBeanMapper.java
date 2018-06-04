package pers.li.dao;

import pers.li.entity.UserBean;

import java.util.List;

public interface UserBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserBean record);

    int insertSelective(UserBean record);

    UserBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserBean record);

    int updateByPrimaryKey(UserBean record);

    UserBean getPassBYUserName(String userName);

    List<String> getRolesBYUser(String userName);

    List<String> getPermissionBYUser(String s);
}