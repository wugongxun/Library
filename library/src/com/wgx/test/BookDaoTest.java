package com.wgx.test;

import com.wgx.dao.BookDao;
import com.wgx.dao.impl.BookDaoImpl;
import com.wgx.pojo.Book;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookDaoTest {
    BookDaoImpl bookDao = new BookDaoImpl();

    @Test
    void addBook() {
        bookDao.addBook(new Book(null, "java核心卷一", 79, "wgx", 100, 300, null));
    }

    @Test
    void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    void updateBook() {
        bookDao.updateBook(new Book(21, "java核心卷一", 79, "wgx", 100, 100, null));
    }

    @Test
    void queryBookById() {
        Book book = bookDao.queryBookById(21);
        System.out.println(book);
    }

    @Test
    void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    void queryItemsCountByPrice() {
        int count = bookDao.queryItemsCountByPrice(10, 100);
        System.out.println(count);
    }

    @Test
    void queryPageItemsByPrice() {
        List<Book> books = bookDao.queryPageItemsByPrice(0, 4, 10, 100);
        System.out.println(books);
    }
}