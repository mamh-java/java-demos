package com.mamh.spring.demo.dao;

public interface BookShopDao {

    Float findBookPriceByIsbn(String isbn);


    /**
     * 更新书的库存
     *
     * @param isbn
     */
    void updateBookStock(String isbn);


    /**
     * 更新用户的余额
     *
     * @param username
     * @param price
     */
    void updateAccountBalance(String username, float price);

}
