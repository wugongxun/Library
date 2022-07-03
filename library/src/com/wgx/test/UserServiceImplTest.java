package com.wgx.test;

import com.wgx.pojo.User;
import com.wgx.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Test
    void registerUser() {

    }

    @Test
    void login() {
        System.out.println(new UserServiceImpl().login(new User("wgx", "123456", "2297665453@qq.com")) == null ? "失败" : "成功");
    }

    @Test
    void existUsername() {
        System.out.println(new UserServiceImpl().existUsername("吴功勋") ? "用户名不可用" : "用户名可用");
    }
}