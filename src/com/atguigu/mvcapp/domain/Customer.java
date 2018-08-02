package com.atguigu.mvcapp.domain;


import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import java.io.Serializable;

public class Customer implements Serializable, HttpSessionBindingListener, HttpSessionActivationListener {
    private Integer id;
    private String name;
    private String address;
    private String phone;

    private String cardType;
    private String cardId;


    public Customer() {
    }

    public Customer(String name, String address, String cardType, String cardId) {
        this.name = name;
        this.address = address;
        this.cardType = cardType;
        this.cardId = cardId;
    }

    public Customer(String name, String address, String phone) {
        this(0, name, address, phone);
    }

    public Customer(Integer id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        httpSessionBindingEvent.getName();
        httpSessionBindingEvent.getValue();
        httpSessionBindingEvent.getSession();
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void sessionWillPassivate(HttpSessionEvent httpSessionEvent) {
        //从内存中写入到硬盘,该类要实现Serializable接口，类的序列化
        httpSessionEvent.getSession();
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent httpSessionEvent) {
        //从硬盘中读取
    }
}
