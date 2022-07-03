package com.wgx.pojo;

import java.util.LinkedList;
import java.util.List;

public class Cart {
    private int userId;
    private List<CartItem> cartItems = new LinkedList<>();

    public Cart(int userId, List<CartItem> cartItems) {
        this.userId = userId;
        this.cartItems = cartItems;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTotalCount() {
        int totalCount = 0;
        for (CartItem cartItem : cartItems) {
            totalCount += cartItem.getCount();
        }
        return totalCount;
    }


    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getTotalPrice();
        }
        return totalPrice;
    }


    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "userId=" + userId +
                "totalCount=" + getTotalCount() +
                "totalPrice=" + getTotalPrice() +
                ", cartItems=" + cartItems +
                '}';
    }
}
