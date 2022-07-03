package com.wgx.service;

import com.wgx.pojo.Book;
import com.wgx.pojo.Page;

import java.util.List;

public interface BookService {
    void addBook(Book book);
    void deleteBookById(int id);
    void updateBook(Book book);
    Book queryBookById(int id);
    List<Book> queryBooks();
    Page<Book> page(int pageNo, int pageSize);
    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
