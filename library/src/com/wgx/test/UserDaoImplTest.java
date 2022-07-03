package com.wgx.test;

import com.wgx.dao.UserDao;
import com.wgx.dao.impl.UserDaoImpl;
import com.wgx.pojo.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {

    @Test
    void queryUserByUsername() {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.queryUserByUsername("wgx"));
    }

    @Test
    void queryUserByUsernameAndPassword() {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.queryUserByUsernameAndPassword("wgx", "123456"));
    }

    @Test
    void saveUser() {
        UserDao userDao = new UserDaoImpl();
        int saveUser = userDao.saveUser(new User("wugongxun", "123456", "wugongxun@gmail.com"));
        System.out.println(saveUser > 0 ? "成功" : "失败");
    }
}