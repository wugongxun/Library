package com.wgx.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {
    /**
     * 工具类，获取连接池中的连接
     */
    private static DataSource dataSource;
    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();//创建一个connection关联的ThreadLocal
    static {
        Properties properties = new Properties();
        try {
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static Connection getConnection() {
        //从TreadLocal中获取connection，如有直接return，如果没有从数据库连接池中获取连接
        Connection connection = connectionThreadLocal.get();
        if (connection == null) {
            try {
                connection = dataSource.getConnection();
                connectionThreadLocal.set(connection);//将获取的连接保存到ThreadLocal中
                connection.setAutoCommit(false);//设置关闭自动提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * 提交并关闭连接
     */
    public static void commitAndClose() {
        Connection connection = connectionThreadLocal.get();
        if (connection != null) {
            try {
                connection.commit();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();//关闭连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        connectionThreadLocal.remove();
    }

    /**
     * 回滚并关闭连接
     */
    public static void rollbackAndClose() {
        Connection connection = connectionThreadLocal.get();
        if (connection != null) {
            try {
                connection.rollback();//回滚事务
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();//关闭连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        connectionThreadLocal.remove();
    }

//    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
//        try {
//            if(resultSet != null) {
//                resultSet.close();
//            }
//            if(statement != null) {
//                statement.close();
//            }
//            if(connection != null) {
//                connection.close();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
