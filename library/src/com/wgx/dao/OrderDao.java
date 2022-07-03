package com.wgx.dao;

import com.wgx.pojo.Order;

import java.util.List;

public interface OrderDao {
    /**
     * 保存订单
     * @param order
     */
    void saveOrder(Order order);

    /**
     * 查询全部订单
     * @return
     */
    List<Order> queryAllOrder();

    /**
     * 修改订单状态
     * @param orderId
     * @param status
     */
    void updateOrderStatus(String orderId, String status);

    /**
     * 根据用户id查询用户所有订单
     * @param userId
     * @return
     */
    List<Order> queryOrderByUserId(int userId);

}
