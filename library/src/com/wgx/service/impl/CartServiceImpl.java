package com.wgx.service.impl;

import com.wgx.dao.CartItemDao;
import com.wgx.dao.impl.CartItemDaoImpl;
import com.wgx.pojo.CartItem;
import com.wgx.service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    CartItemDao cartItemDao = new CartItemDaoImpl();
    @Override
    public void addItem(CartItem cartItem) {
        cartItemDao.addItem(cartItem);
    }

    @Override
    public void updateCount(int userId, int itemId, int count) {
        cartItemDao.updateCount(userId, itemId, count);
    }

    @Override
    public void deleteItem(int userId, int itemId) {
        cartItemDao.deleteItem(userId, itemId);
    }

    @Override
    public void clearCart(int userId) {
        cartItemDao.clearCart(userId);
    }

    @Override
    public List<CartItem> listCart(int userId) {
        return cartItemDao.listCart(userId);
    }

    @Override
    public int getTotalCount(List<CartItem> cartItems) {
        int totalCount = 0;
        for (CartItem cartItem : cartItems) {
            totalCount += cartItem.getCount();
        }
        return totalCount;
    }

    @Override
    public double getTotalPrice(List<CartItem> cartItems) {
        double totalPrice = 0.0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getTotalPrice();
        }
        return totalPrice;
    }

}
