package com.wgx.test;

import com.wgx.utils.JdbcUtils;

import java.sql.Connection;

public class JdbcTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection);
        }
    }
}
