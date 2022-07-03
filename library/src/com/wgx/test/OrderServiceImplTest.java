package com.wgx.test;

import com.wgx.pojo.Cart;
import com.wgx.pojo.CartItem;
import com.wgx.pojo.Order;
import com.wgx.pojo.OrderItem;
import com.wgx.service.CartService;
import com.wgx.service.OrderService;
import com.wgx.service.impl.CartServiceImpl;
import com.wgx.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    OrderService orderService = new OrderServiceImpl();
    CartService cartService = new CartServiceImpl();
    @Test
    void createOrder() {
        List<CartItem> cartItems = cartService.listCart(3);
        orderService.createOrder(new Cart(3, cartItems), 3);
    }

    @Test
    void listOrders() {
        List<Order> orders = orderService.listOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    void showOrderDetail() {
        List<OrderItem> orderItems = orderService.showOrderDetail("20211129204749");
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
    }

    @Test
    void listMyOrders() {
        List<Order> orders = orderService.listOrders();
        System.out.println(orders);
    }

    @Test
    void receivedOrder() {
        orderService.receivedOrder("20211129204749");
    }

    @Test
    void deliverGoods() {
        orderService.deliverGoods("20211129204749");
    }
}