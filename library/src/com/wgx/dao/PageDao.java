package com.wgx.dao;


import java.util.List;

public interface PageDao<T> {
    /**
     * 查询总的数据数量
     * @return
     */
    int queryItemsCount();

    /**
     * 查询当前页面的数据
     * @param begin 页面开始的位置
     * @param pageSize 页面显示的数量
     * @return
     */
    List<T> queryPageItems(int begin, int pageSize);

    /**
     * 查询价格区间内的数据总数
     * @return
     */
    int queryItemsCountByPrice(int max, int min);

    /**
     * 查询价格区间内的页面数据
     * @return
     */
    List<T> queryPageItemsByPrice(int begin, int size, int max, int min);
}
