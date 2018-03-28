package com.mamh.spring.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {

    @Autowired
    private BookShopDao bookShopDao;

    @Override
    @Transactional//添加事务注解
    public void purchase(String username, String isbn) {
        //1.获取书的单价
        Float price = bookShopDao.findBookPriceByIsbn(isbn);

        //2.更新书的库存
        bookShopDao.updateBookStock(isbn);
        //这个时候还没设置事务，我们先减少库存能成功，然后减少余额失败了。但是库存还是正常的减少了，这不是我们想要的。

        //下面我们要加上事务，使用注解方式。

        //3.更新用户余额
        bookShopDao.updateAccountBalance(username, price);
    }
}
