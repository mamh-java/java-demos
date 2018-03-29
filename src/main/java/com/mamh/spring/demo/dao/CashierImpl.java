package com.mamh.spring.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashierImpl implements Cashier {

    @Autowired
    private BookShopService bookShopService;

    public void setBookShopService(BookShopService bookShopService) {
        this.bookShopService = bookShopService;
    }

    @Override
    public void checkout(String username, List<String> isbns) {
        for (String isbn : isbns) {
            bookShopService.purchase(username, isbn);
        }

    }
}
