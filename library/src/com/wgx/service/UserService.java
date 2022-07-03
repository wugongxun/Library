package com.wgx.service;

import com.wgx.pojo.User;

public interface UserService {
    /**
     * 注册
     * @param user
     */
    void registerUser(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 检查用户名是否存在
     * @param username
     * @return
     */
    boolean existUsername(String username);
}
