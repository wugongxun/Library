package com.wgx.dao;

import com.wgx.pojo.CartItem;

import java.util.List;

public interface CartItemDao {
    /**
     * 添加一个商品到数据库
     * @param cartItem
     */
    void addItem(CartItem cartItem);

    /**
     * 从userId用户的购物车中删除一个商品编号为goodId的商品
     * @param userId
     * @param itemId
     */
    void deleteItem(int userId, int itemId);

    /**
     * 修改userId用户的购物车中的编号为goodId的商品数量为count
     * @param userId
     * @param itemId
     * @param count
     */
    void updateCount(int userId, int itemId, int count);

    /**
     * 清空userId用户的购物车
     * @param userId
     */
    void clearCart(int userId);

    /**
     * 根据userId和goodId查找是否有该商品存在在购物车中
     * @param userId
     * @param itemId
     * @return
     */
    CartItem findCartItem(int userId, int itemId);

    /**
     * 查询userId购物车中的所有商品
     * @param userId
     * @return
     */
    List<CartItem> listCart(int userId);
}
