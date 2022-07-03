package com.wgx.web;

import com.wgx.pojo.Book;
import com.wgx.pojo.Page;
import com.wgx.service.BookService;
import com.wgx.service.impl.BookServiceImpl;
import com.wgx.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();

    protected void addBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Book book = WebUtils.copyParamToBean(new Book(), req.getParameterMap());
        bookService.addBook(book);
        int pageNo = Integer.parseInt(req.getParameter("pageNo")) + 1;
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        bookService.deleteBookById(Integer.parseInt(id));
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Book book = WebUtils.copyParamToBean(new Book(), req.getParameterMap());
        bookService.updateBook(book);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Book book = bookService.queryBookById(Integer.parseInt(id));
        req.setAttribute("book", book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("manager/bookServlet?action=page&pageNo=");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
