package com.wgx.service.impl;

import com.wgx.dao.UserDao;
import com.wgx.dao.impl.UserDaoImpl;
import com.wgx.pojo.User;
import com.wgx.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        return userDao.queryUserByUsername(username) != null;
    }
}
