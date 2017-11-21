package com.mamh.hibernate.demo.entities;

public class Department {
    private int depatmentId;
    private String departmentName;

    private Manager manager;

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public int getDepatmentId() {
        return depatmentId;
    }

    public void setDepatmentId(int depatmentId) {
        this.depatmentId = depatmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
