package com.mamh.spring.demo.beans;

import javax.persistence.*;

@Entity
@Table(name = "sp_account", schema = "atguigu", catalog = "")
public class Account {
    private int id;
    private String username;
    private float balance;


    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 1024)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "balance", nullable = true, precision = 0)
    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (balance != +0.0f ? Float.floatToIntBits(balance) : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (id != account.id) return false;
        if (Float.compare(account.balance, balance) != 0) return false;
        if (username != null ? !username.equals(account.username) : account.username != null) return false;

        return true;
    }
}
