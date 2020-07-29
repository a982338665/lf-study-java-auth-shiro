package pers.li.boot.service;

import pers.li.boot.entities.User;

public interface UserService {


    public User getUserById(Integer id);
    public void insertUser(String name, String sex);
}
