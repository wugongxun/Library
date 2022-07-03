package com.wgx.dao.impl;

import com.wgx.dao.BookDao;
import com.wgx.dao.PageDao;
import com.wgx.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao<Book> implements BookDao, PageDao<Book> {
    @Override
    public int addBook(Book book) {
        String sql = "insert into book values(?, ?, ?, ?, ?, ?, ?)";
        return update(sql, null, book.getName(), book.getPrice(), book.getAuthor(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(int id) {
        String sql = "delete from book where id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update book set name = ?, price = ?, author = ?, sales = ?, stock = ?, img_path = ? where id = ?";
        return update(sql, book.getName(), book.getPrice(), book.getAuthor(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
    }

    @Override
    public Book queryBookById(int id) {
        String sql = "select id, name, price, author, sales, stock, img_path as imgPath from book where id = ?";
        return querySingle(sql, Book.class, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select * from book";
        return queryMultiple(sql, Book.class);
    }

    @Override
    public int queryItemsCount() {
        String sql = "select count(*) from book";
        Number count = (Number)queryScalar(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryPageItems(int begin, int pageSize) {
        String sql = "select * from book limit ?, ?";
        return queryMultiple(sql, Book.class, begin, pageSize);
    }

    @Override
    public int queryItemsCountByPrice(int min, int max) {
        if (min < max) {
            String sql = "select count(*) from book where price between ? and ?";
            Number count = (Number) queryScalar(sql, min, max);
            return count.intValue();
        }else if (min == max) {
            String sql = "select count(*) from book where price = ?";
            Number count = (Number) queryScalar(sql, min);
            return count.intValue();
        }else {
            return queryItemsCount();
        }
    }

    @Override
    public List<Book> queryPageItemsByPrice(int begin, int size, int min, int max) {
        if (min < max) {
            String sql = "select * from book where price between ? and ? limit ?, ?";
            return queryMultiple(sql, Book.class, min, max, begin, size);
        }else if (min == max) {
            String sql = "select * from book where price = ? limit ?, ?";
            return queryMultiple(sql, Book.class, min, begin, size);
        }else {
            return queryPageItems(begin, size);
        }
    }
}
