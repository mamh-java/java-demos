package com.mamh.spring.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookShopServiceImpl implements BookShopService {

    @Autowired
    private BookShopDao bookShopDao;

    public void setBookShopDao(BookShopDao bookShopDao) {
        this.bookShopDao = bookShopDao;
    }

    /**
     * 1.事务的传播行为，默认是用调用者的那个事务Propagation.REQUIRED。
     * 2.事务的隔离级别，常用的是读已提交Isolation.READ_COMMITTED
     * 3.默认情况下是对所有runtime异常进行回滚。通常情况采用默认值就行。
     * 4.只读事务，readonly=true来设置。
     * 5.使用timeout只读强制回滚之前事务可以占用的时间。
     *
     * @param username
     * @param isbn
     */
    public void purchase(String username, String isbn) {
        System.out.println("获取书的价格");
        //1.获取书的单价
        Float price = bookShopDao.findBookPriceByIsbn(isbn);
        System.out.println("\n");


        System.out.println("获取书的库存");
        //2.更新书的库存
        bookShopDao.updateBookStock(isbn);
        //这个时候还没设置事务，我们先减少库存能成功，然后减少余额失败了。但是库存还是正常的减少了，这不是我们想要的。
        System.out.println("\n");


        System.out.println("更新账户余额");
        //3.更新用户余额
        bookShopDao.updateAccountBalance(username, price);
    }
}
