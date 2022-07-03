package com.wgx.web;

import com.wgx.pojo.Book;
import com.wgx.pojo.Cart;
import com.wgx.pojo.Page;
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

public class ClientBookServlet extends BaseServlet{
    BookService bookService = new BookServiceImpl();
    CartService cartService = new CartServiceImpl();
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/bookServlet?action=page");
        req.setAttribute("page", page);
        User loginUser = (User)req.getSession().getAttribute("loginUser");
        if (loginUser != null) {
            loginUser.setCart(new Cart(loginUser.getId(), cartService.listCart(loginUser.getId())));
        }
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);
        page.setUrl("client/bookServlet?action=pageByPrice&min=" + min + "&max=" + max);
        req.setAttribute("page", page);
        User loginUser = (User)req.getSession().getAttribute("loginUser");
        if (loginUser != null) {
            loginUser.setCart(new Cart(loginUser.getId(), cartService.listCart(loginUser.getId())));
        }
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
