package pers.li.boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.li.boot.dao.common.UserDao;
import pers.li.boot.entities.User;
import pers.li.boot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer id) {
        return userDao.selectUserById(id);
    }

    @Override
    public void insertUser(String name, String sex) {
        userDao.insert(name, sex);
    }
}
