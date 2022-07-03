package com.wgx.dao;

import com.wgx.pojo.Book;

import java.util.List;

public interface BookDao {
    int addBook(Book book);
    int deleteBookById(int id);
    int updateBook(Book book);
    Book queryBookById(int id);
    List<Book> queryBooks();
}
