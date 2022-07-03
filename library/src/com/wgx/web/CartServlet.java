package com.wgx.web;


import com.wgx.pojo.Book;
import com.wgx.pojo.Cart;
import com.wgx.pojo.CartItem;
import com.wgx.pojo.User;
import com.wgx.service.BookService;
import com.wgx.service.CartService;
import com.wgx.service.impl.BookServiceImpl;
import com.wgx.service.impl.CartServiceImpl;
import com.wgx.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CartServlet extends BaseServlet {
    CartService cartService = new CartServiceImpl();
    BookService bookService = new BookServiceImpl();

    protected void addCartItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = WebUtils.parseInt(req.getParameter("userId"), 0);
        int itemId = WebUtils.parseInt(req.getParameter("itemId"), 0);
        Book book = bookService.queryBookById(itemId);
        cartService.addItem(new CartItem(userId, itemId, book.getName(), 1, book.getPrice()));
        req.getSession().setAttribute("addItemName", book.getName());
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = WebUtils.parseInt(req.getParameter("userId"), 0);
        int itemId = WebUtils.parseInt(req.getParameter("itemId"), 0);
        cartService.deleteItem(userId, itemId);
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        loginUser.setCart(new Cart(loginUser.getId(), cartService.listCart(loginUser.getId())));
        resp.sendRedirect(req.getContextPath() + "/pages/cart/cart.jsp");
    }

    protected void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = WebUtils.parseInt(req.getParameter("userId"), 0);
        cartService.clearCart(userId);
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        loginUser.setCart(new Cart(loginUser.getId(), cartService.listCart(loginUser.getId())));
        resp.sendRedirect(req.getContextPath() + "/pages/cart/cart.jsp");
    }
}
