package com.wgx.web;

import com.wgx.pojo.*;
import com.wgx.service.CartService;
import com.wgx.service.OrderService;
import com.wgx.service.impl.CartServiceImpl;
import com.wgx.service.impl.OrderServiceImpl;
import com.wgx.utils.JdbcUtils;
import com.wgx.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet{
    OrderService orderService = new OrderServiceImpl();
    CartService cartService = new CartServiceImpl();
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = WebUtils.parseInt(req.getParameter("userId"), 0);
        List<CartItem> cartItems = cartService.listCart(userId);
        orderService.createOrder(new Cart(userId, cartItems), userId);
        req.getRequestDispatcher("/orderServlet?action=listMyOrders").forward(req, resp);
    }
    protected void listOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderService.listOrders();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/pages/order/order_manager.jsp").forward(req, resp);
    }

    protected void listMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = (User)req.getSession().getAttribute("loginUser");
        List<Order> orders = orderService.listMyOrders(loginUser.getId());
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/pages/order/orders.jsp").forward(req, resp);
    }

    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        String from = req.getParameter("from");
        String status = req.getParameter("status");
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        req.setAttribute("orderItems", orderItems);
        req.setAttribute("orderId", orderId);
        req.setAttribute("from", from);
        req.setAttribute("status", status);
        req.getRequestDispatcher("/pages/order/order_detail.jsp").forward(req, resp);
    }

    protected void receivedOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.receivedOrder(orderId);
        req.getRequestDispatcher("/orderServlet?action=listMyOrders").forward(req, resp);
    }
    protected void deliverGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.deliverGoods(orderId);
        req.getRequestDispatcher("/orderServlet?action=listOrders").forward(req, resp);
    }
}
