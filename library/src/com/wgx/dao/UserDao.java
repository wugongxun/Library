package com.wgx.dao;

import com.wgx.pojo.User;

public interface UserDao {
    User queryUserByUsername(String username);
    User queryUserByUsernameAndPassword(String username, String password);
    int saveUser(User user);
}
