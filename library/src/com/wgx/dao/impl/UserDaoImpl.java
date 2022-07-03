package com.wgx.dao.impl;

import com.wgx.dao.UserDao;
import com.wgx.pojo.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select * from user where username = ?";
        return querySingle(sql, User.class, username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select * from user where username = ? and password = md5(?)";
        return querySingle(sql, User.class, username, password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into user(username, password, email) values(?, md5(?), ?)";
        return update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }
}
