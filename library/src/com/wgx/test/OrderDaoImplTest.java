package com.wgx.test;

import com.wgx.dao.OrderDao;
import com.wgx.dao.impl.OrderDaoImpl;
import com.wgx.pojo.Order;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


class OrderDaoImplTest {
    OrderDao orderDao = new OrderDaoImpl();
    @Test
    void saveOrder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        orderDao.saveOrder(new Order(sdf.format(new Date()), LocalDateTime.now(), 100, "已发货", 3));
    }

    @Test
    void queryAllOrder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Order> orders = orderDao.queryAllOrder();
        for (Order order : orders) {
            System.out.println(order.getCreateTimeFormat());
        }
    }

    @Test
    void updateOrderStatus() {
        orderDao.updateOrderStatus("20211128045559", "未发货");
    }

    @Test
    void queryOrderByUserId() {
        List<Order> orders = orderDao.queryOrderByUserId(3);
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}