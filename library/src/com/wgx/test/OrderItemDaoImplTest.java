package com.wgx.test;

import com.wgx.dao.OrderItemDao;
import com.wgx.dao.impl.OrderItemDaoImpl;
import com.wgx.pojo.OrderItem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemDaoImplTest {
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    @Test
    void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem("java从入门到放弃", 2, 80.0, 160.0, "20211128045559"));
    }

    @Test
    void queryOrderItemByOrderId() {
        List<OrderItem> orderItems = orderItemDao.queryOrderItemByOrderId("20211129204547");
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
    }
}