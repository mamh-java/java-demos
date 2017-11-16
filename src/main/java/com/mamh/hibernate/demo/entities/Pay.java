package com.mamh.hibernate.demo.entities;

public class Pay {
    private int monthlyPay;
    private int yearPay;
    private int vacation;

    public int getMonthlyPay() {
        return monthlyPay;
    }

    public void setMonthlyPay(int monthlyPay) {
        this.monthlyPay = monthlyPay;
    }

    public int getYearPay() {
        return yearPay;
    }

    public void setYearPay(int yearPay) {
        this.yearPay = yearPay;
    }

    public int getVacation() {
        return vacation;
    }

    public void setVacation(int vacation) {
        this.vacation = vacation;
    }
}
