package pers.li.dao;

import pers.li.entity.PermissionBean;

public interface PermissionBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PermissionBean record);

    int insertSelective(PermissionBean record);

    PermissionBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PermissionBean record);

    int updateByPrimaryKey(PermissionBean record);
}