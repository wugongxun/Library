package com.wgx.service.impl;

import com.wgx.dao.BookDao;
import com.wgx.dao.OrderDao;
import com.wgx.dao.OrderItemDao;
import com.wgx.dao.impl.BookDaoImpl;
import com.wgx.dao.impl.OrderDaoImpl;
import com.wgx.dao.impl.OrderItemDaoImpl;
import com.wgx.pojo.*;
import com.wgx.service.OrderService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    BookDao bookDao = new BookDaoImpl();
    @Override
    public void createOrder(Cart cart, int userId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderId = sdf.format(new Date());
        orderDao.saveOrder(new Order(orderId, LocalDateTime.now(), cart.getTotalPrice(), "未发货", userId));
        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems) {
            orderItemDao.saveOrderItem(new OrderItem(cartItem.getItemName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId));
            Book book = bookDao.queryBookById(cartItem.getItemId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }
    }

    @Override
    public List<Order> listOrders() {
        return orderDao.queryAllOrder();
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemByOrderId(orderId);
    }

    @Override
    public List<Order> listMyOrders(int userId) {
        return orderDao.queryOrderByUserId(userId);
    }

    @Override
    public void receivedOrder(String orderId) {
        orderDao.updateOrderStatus(orderId, "已完成");
    }

    @Override
    public void deliverGoods(String orderId) {
        orderDao.updateOrderStatus(orderId, "已发货");
    }

}
