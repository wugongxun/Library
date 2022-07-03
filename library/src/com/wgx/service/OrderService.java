package com.wgx.service;

import com.wgx.pojo.Cart;
import com.wgx.pojo.Order;
import com.wgx.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    /**
     * 生成订单
     * @param cart
     * @param userId
     */
    void createOrder(Cart cart, int userId);

    /**
     * 查询全部订单
     * @return
     */
    List<Order> listOrders();

    /**
     * 查看订单详情
     * @param orderId
     * @return
     */
    List<OrderItem> showOrderDetail(String orderId);

    /**
     * 查看我的订单
     * @param userId
     * @return
     */
    List<Order> listMyOrders(int userId);

    /**
     * 确认收货
     * @param orderId
     */
    void receivedOrder(String orderId);


    /**
     * 发货
     * @param orderId
     */
    void deliverGoods(String orderId);
}
