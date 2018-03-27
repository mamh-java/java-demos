package com.mamh.spring.demo.beans;


public class Customer {


    private Integer customerId;
    private String customerName;

    public Customer() {
    }

    public Customer(String name) {
        this.customerName = name;
    }


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "\nCustomer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                '}';
    }

}
