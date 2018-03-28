package com.mamh.spring.demo.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class BookShopDaoImpl implements BookShopDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Float findBookPriceByIsbn(String isbn) {
        String sql = "SELECT price FROM  sp_book WHERE isbn = ?";
        Float aFloat = jdbcTemplate.queryForObject(sql, Float.class, isbn);
        return aFloat;
    }

    @Override
    public void updateBookStock(String isbn) {
        //需要手动检查库存是否充足。不够就抛出异常
        String sql;
        sql = "SELECT stock FROM sp_stock WHERE isbn = ?";
        int integer = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
        if (integer <= 0) {
            throw new RuntimeException("库存不足");
        }


        sql = "UPDATE sp_stock SET stock = stock-1 WHERE isbn = ?";
        jdbcTemplate.update(sql, isbn);
    }

    @Override
    public void updateAccountBalance(String username, float price) {
        String sql;
        sql = "SELECT balance FROM sp_account WHERE username= ?";
        Float balance = jdbcTemplate.queryForObject(sql, Float.class, username);
        if (balance < price) {
            throw new RuntimeException("余额不足");
        }

        sql = "UPDATE sp_account SET balance = balance - ? WHERE username = ?";
        jdbcTemplate.update(sql, price, username);
    }
}
