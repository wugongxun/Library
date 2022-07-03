package com.wgx.dao.impl;

import com.wgx.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BaseDao<T> {
    private QueryRunner queryRunner = new QueryRunner();
    //通用的dml方法
    public int update(String sql, Object... parameters) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection, sql, parameters);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    //返回多个对象，针对任意的表
    public List<T> queryMultiple(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<T>(clazz), parameters);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    //查询单行结果的通用方法
    public T querySingle(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanHandler<T>(clazz), parameters);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    //查询单行单列的通用方法
    public Object queryScalar(String sql, Object... parameters) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new ScalarHandler<T>(), parameters);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
