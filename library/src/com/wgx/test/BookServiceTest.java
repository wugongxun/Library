package com.wgx.test;

import com.wgx.pojo.Book;
import com.wgx.pojo.Page;
import com.wgx.service.BookService;
import com.wgx.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    BookService bookService = new BookServiceImpl();

    @Test
    void addBook() {
        bookService.addBook(new Book(null, "core java", 79, "wgx", 1000, 2000, null));
    }

    @Test
    void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    void updateBook() {
        bookService.updateBook(new Book(22, "core java", 79, "wgx", 500, 2000, null));
    }

    @Test
    void queryBookById() {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    void queryBooks() {
        List<Book> books = bookService.queryBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }
    @Test
    void page() {
        Page<Book> page = bookService.page(1, Page.PAGE_SIZE);
        System.out.println(page);
    }
    @Test
    void pageByPrice() {
        Page<Book> bookPage = bookService.pageByPrice(1, Page.PAGE_SIZE, 10, 100);
        System.out.println(bookPage);
    }
}