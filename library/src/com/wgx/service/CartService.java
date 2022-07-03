package com.wgx.service;

import com.wgx.pojo.CartItem;

import java.util.List;

public interface CartService {

    void addItem(CartItem cartItem);
    void updateCount(int userId, int itemId, int count);
    void deleteItem(int userId, int itemId);
    void clearCart(int userId);
    List<CartItem> listCart(int userId);

    /**
     * 求总数量
     * @param cartItems
     * @return
     */
    int getTotalCount(List<CartItem> cartItems);

    /**
     * 求总金额
     * @param cartItems
     * @return
     */
    double getTotalPrice(List<CartItem> cartItems);
}
