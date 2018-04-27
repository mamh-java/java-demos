package com.mamh.ssh.demo.entities;

public class Department {

    private Integer id;
    private String deptName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "\nDepartment{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                '}' + '\n';
    }
}
