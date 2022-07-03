package com.wgx.test;

import com.wgx.dao.CartItemDao;
import com.wgx.dao.impl.CartItemDaoImpl;
import com.wgx.pojo.CartItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartItemDaoImplTest {

    CartItemDao cartItemDao = new CartItemDaoImpl();
    @Test
    void addItem() {
        cartItemDao.addItem(new CartItem(3, 2, "数据结构与算法", 1, 78.5));
    }

    @Test
    void deleteItem() {
        cartItemDao.deleteItem(3, 2);
    }

    @Test
    void updateCount() {
        cartItemDao.updateCount(3, 1, 1);
    }

    @Test
    void clearCart() {
        cartItemDao.clearCart(3);
    }

    @Test
    void findCartItem() {
        CartItem cartItem = cartItemDao.findCartItem(3, 1);
        System.out.println(cartItem);
    }
}