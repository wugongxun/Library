package com.wgx.service.impl;

import com.wgx.dao.impl.BookDaoImpl;
import com.wgx.pojo.Book;
import com.wgx.pojo.Page;
import com.wgx.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDaoImpl bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(int id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(int id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();
        int itemsCount = bookDao.queryItemsCount();
        int pageCount = (int) Math.ceil(itemsCount * 1.0 / pageSize);
        if (pageNo < 1) {
            pageNo = 1;
        }else if (pageNo >= pageCount) {
            pageNo = pageCount;
        }
        page.setPageNo(pageNo);
        page.setPageCount(pageCount);
        page.setItemsCount(itemsCount);
        int begin = (pageNo - 1) * pageSize;
        page.setPageItems(bookDao.queryPageItems(begin, pageSize));
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();
        int itemsCount = bookDao.queryItemsCountByPrice(min, max);
        if (itemsCount == 0) {
            return page;
        }else {
            int pageCount = (int) Math.ceil(itemsCount * 1.0 / pageSize);
            if (pageNo < 1) {
                pageNo = 1;
            }else if (pageNo > pageCount) {
                pageNo = pageCount;
            }
            page.setPageNo(pageNo);
            page.setPageCount(pageCount);
            page.setItemsCount(itemsCount);
            int begin = (pageNo - 1) * pageSize;
            page.setPageItems(bookDao.queryPageItemsByPrice(begin, pageSize, min, max));
            return page;
        }
    }
}
