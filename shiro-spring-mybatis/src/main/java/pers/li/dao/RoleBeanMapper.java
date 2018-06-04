package pers.li.dao;

import pers.li.entity.RoleBean;

public interface RoleBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleBean record);

    int insertSelective(RoleBean record);

    RoleBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleBean record);

    int updateByPrimaryKey(RoleBean record);
}