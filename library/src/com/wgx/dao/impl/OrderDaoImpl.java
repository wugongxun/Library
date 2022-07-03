package com.wgx.dao.impl;

import com.wgx.dao.OrderDao;
import com.wgx.pojo.Order;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public void saveOrder(Order order) {
        String sql = "insert into `order` values(?, ?, ?, ?, ?)";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());

    }

    @Override
    public List<Order> queryAllOrder() {
        String sql = "select order_id as orderId, create_time as createTime, price, status, user_id as userId from `order`";
        return queryMultiple(sql, Order.class);
    }

    @Override
    public void updateOrderStatus(String orderId, String status) {
        String sql = "update `order` set status = ? where order_id = ?";
        update(sql, status, orderId);
    }

    @Override
    public List<Order> queryOrderByUserId(int userId) {
        String sql = "select order_id as orderId, create_time as createTime, price, status, user_id as userId from `order` where user_id = ?";
        return queryMultiple(sql, Order.class, userId);
    }

}
