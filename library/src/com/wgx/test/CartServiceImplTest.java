package com.wgx.test;

import com.wgx.pojo.CartItem;
import com.wgx.service.CartService;
import com.wgx.service.impl.CartServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartServiceImplTest {
    CartService cartService = new CartServiceImpl();
    @Test
    void addItem() {
        cartService.addItem(new CartItem(3, 2, "数据结构与算法", 1, 78.5));
    }

    @Test
    void updateCount() {
        cartService.updateCount(3, 1, 2);
    }

    @Test
    void deleteItem() {
        cartService.deleteItem(3, 1);
    }

    @Test
    void clearCart() {
        cartService.clearCart(3);
    }

    @Test
    void listCart() {
        List<CartItem> cartItems = cartService.listCart(3);
        for (CartItem cartItem : cartItems) {
            System.out.println(cartItem);
        }
    }

    @Test
    void getTotalCount() {
        List<CartItem> cartItems = cartService.listCart(3);
        int totalCount = cartService.getTotalCount(cartItems);
        System.out.println(totalCount);
    }

    @Test
    void getTotalPrice() {
        List<CartItem> cartItems = cartService.listCart(3);
        double totalPrice = cartService.getTotalPrice(cartItems);
        System.out.println(totalPrice);
    }
}