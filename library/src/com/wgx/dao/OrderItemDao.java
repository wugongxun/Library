package com.wgx.dao;

import com.wgx.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    /**
     * 保存订单项
     * @param orderItem
     */
    void saveOrderItem(OrderItem orderItem);

    /**
     * 根据订单id查询订单详情，显示订单下的所有订单项
     * @param orderId
     * @return
     */
    List<OrderItem> queryOrderItemByOrderId(String orderId);
}
