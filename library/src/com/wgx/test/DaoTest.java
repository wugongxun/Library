package com.wgx.test;

import com.wgx.dao.impl.UserDaoImpl;
import com.wgx.pojo.User;

import java.util.List;

public class DaoTest {
    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
        String sql1 = "select * from user where id = ?";
        User admin = userDao.querySingle(sql1, User.class, 1);
        System.out.println(admin);

        String sql2 = "select username from user where id = ?";
        Object scalar = userDao.queryScalar(sql2, 1);
        System.out.println(scalar);

//        String sql3 = "insert into user(username, password, email) values('wgx', md5('123456'), '2297665453@qq.com')";
//        int update = userDao.update(sql3);
//        System.out.println(update > 0 ? "成功" : "失败");

        String sql4 = "select * from user";
        List<User> users = userDao.queryMultiple(sql4, User.class);
        for (User user : users) {
            System.out.println(user);
        }

    }
}
