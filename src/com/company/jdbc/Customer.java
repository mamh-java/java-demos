package com.company.jdbc;


import java.io.Serializable;

public class Customer implements Serializable {
    private int id;
    private String name;
    private String address;
    private String phone;

    private String cardType;
    private String cardId;


    public Customer() {
    }

    public Customer(Integer id, String name, String address, String phone, String cardType, String cardId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.cardType = cardType;
        this.cardId = cardId;
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


}
