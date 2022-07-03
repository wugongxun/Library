package com.wgx.dao.impl;

import com.wgx.dao.OrderItemDao;
import com.wgx.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

    @Override
    public void saveOrderItem(OrderItem orderItem) {
        String sql = "insert into order_item values(?, ?, ?, ?, ?, ?)";
        update(sql, null, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        String sql = "select id, name, count, price, total_price as totalPrice, order_id as orderId from order_item where order_id = ?";
        return queryMultiple(sql, OrderItem.class, orderId);
    }
}
