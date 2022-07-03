package com.wgx.pojo;

public class CartItem {
    private int userId;
    private int itemId;
    private String itemName;
    private int count;
    private double price;

    public CartItem() {
    }

    public CartItem(int userId, int itemId, String itemName, int count, double price) {
        this.userId = userId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.count = count;
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        double totalPrice = count * price;
        return totalPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "userId=" + userId +
                ", bookId=" + itemId +
                ", bookName=" + itemName +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }
}
