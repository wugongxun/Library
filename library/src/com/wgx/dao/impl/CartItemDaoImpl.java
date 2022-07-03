package com.wgx.dao.impl;

import com.wgx.dao.CartItemDao;
import com.wgx.pojo.CartItem;

import java.util.List;

public class CartItemDaoImpl extends BaseDao<CartItem> implements CartItemDao {

    @Override
    public void addItem(CartItem cartItem) {
        CartItem item = findCartItem(cartItem.getUserId(), cartItem.getItemId());
        if (item == null) {
            String sql = "insert into cart_item value(?, ?, ?, ?, ?)";
            update(sql, cartItem.getUserId(), cartItem.getItemId(), cartItem.getItemName(), cartItem.getCount(), cartItem.getPrice());
        }else {
            updateCount(item.getUserId(), item.getItemId(), item.getCount() + 1);
        }

    }

    @Override
    public void deleteItem(int userId, int itemId) {
        String sql = "delete from cart_item where user_id = ? and item_id = ?";
        update(sql, userId, itemId);
    }

    @Override
    public void updateCount(int userId, int itemId, int count) {
        String sql ="update cart_item set count = ? where user_id = ? and item_id = ?";
        update(sql, count, userId, itemId);
    }

    @Override
    public void clearCart(int userId) {
        String sql = "delete from cart_item where user_id = ?";
        update(sql, userId);
    }

    @Override
    public CartItem findCartItem(int userId, int itemId) {
        String sql = "select user_id as userId, item_id as itemId, item_name as itemName, count, price from cart_item where user_id = ? and item_id = ?";
        return querySingle(sql, CartItem.class, userId, itemId);
    }

    @Override
    public List<CartItem> listCart(int userId) {
        String sql = "select user_id as userId, item_id as itemId, item_name as itemName, count, price from cart_item where user_id = ?";
        return queryMultiple(sql, CartItem.class, userId);
    }
}
