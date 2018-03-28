package com.mamh.spring.demo.dao;

import java.util.List;

public interface Cashier {

    public void checkout(String username, List<String> isbns);
}
