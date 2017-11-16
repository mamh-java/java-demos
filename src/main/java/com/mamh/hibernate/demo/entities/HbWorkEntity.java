package com.mamh.hibernate.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "hb_work", schema = "atguigu", catalog = "")
public class HbWorkEntity {
    private int hbId;
    private String hbName;
    private Integer hbMonthlyPay;
    private Integer hbYearPay;
    private Integer hbVacation;

    @Id
    @Column(name = "hb_id", nullable = false)
    public int getHbId() {
        return hbId;
    }

    public void setHbId(int hbId) {
        this.hbId = hbId;
    }

    @Basic
    @Column(name = "hb_name", nullable = false, length = 1024)
    public String getHbName() {
        return hbName;
    }

    public void setHbName(String hbName) {
        this.hbName = hbName;
    }

    @Basic
    @Column(name = "hb_monthly_pay", nullable = true)
    public Integer getHbMonthlyPay() {
        return hbMonthlyPay;
    }

    public void setHbMonthlyPay(Integer hbMonthlyPay) {
        this.hbMonthlyPay = hbMonthlyPay;
    }

    @Basic
    @Column(name = "hb_year_pay", nullable = true)
    public Integer getHbYearPay() {
        return hbYearPay;
    }

    public void setHbYearPay(Integer hbYearPay) {
        this.hbYearPay = hbYearPay;
    }

    @Basic
    @Column(name = "hb_vacation", nullable = true)
    public Integer getHbVacation() {
        return hbVacation;
    }

    public void setHbVacation(Integer hbVacation) {
        this.hbVacation = hbVacation;
    }

    @Override
    public int hashCode() {
        int result = hbId;
        result = 31 * result + (hbName != null ? hbName.hashCode() : 0);
        result = 31 * result + (hbMonthlyPay != null ? hbMonthlyPay.hashCode() : 0);
        result = 31 * result + (hbYearPay != null ? hbYearPay.hashCode() : 0);
        result = 31 * result + (hbVacation != null ? hbVacation.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HbWorkEntity that = (HbWorkEntity) o;

        if (hbId != that.hbId) return false;
        if (hbName != null ? !hbName.equals(that.hbName) : that.hbName != null) return false;
        if (hbMonthlyPay != null ? !hbMonthlyPay.equals(that.hbMonthlyPay) : that.hbMonthlyPay != null) return false;
        if (hbYearPay != null ? !hbYearPay.equals(that.hbYearPay) : that.hbYearPay != null) return false;
        if (hbVacation != null ? !hbVacation.equals(that.hbVacation) : that.hbVacation != null) return false;

        return true;
    }
}
