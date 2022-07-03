package com.wgx.test;

import com.wgx.dao.impl.BookDaoImpl;
import com.wgx.pojo.Page;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PageDaoTest {
    BookDaoImpl bookDao = new BookDaoImpl();
    @Test
    void queryItemsCount() {

        System.out.println(bookDao.queryItemsCount());
    }

    @Test
    void queryPageItems() {
        System.out.println(bookDao.queryPageItems(0, Page.PAGE_SIZE));
    }
}