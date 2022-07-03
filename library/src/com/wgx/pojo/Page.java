package com.wgx.pojo;

import java.util.List;

public class Page<T> {
    public final static int PAGE_SIZE = 4;
    //当前页码
    private int pageNo;
    //总页数
    private int pageCount;
    //每页显示数量
    private int pageSize = PAGE_SIZE;
    //总数量
    private int itemsCount;
    //当前页的数据
    private List<T> pageItems;
    //跳转的路径
    private String url;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public List<T> getPageItems() {
        return pageItems;
    }

    public void setPageItems(List<T> pageItems) {
        this.pageItems = pageItems;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageCount=" + pageCount +
                ", pageSize=" + pageSize +
                ", itemsCount=" + itemsCount +
                ", pageItems=" + pageItems +
                ", url='" + url + '\'' +
                '}';
    }
}
