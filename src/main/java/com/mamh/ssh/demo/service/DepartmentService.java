package com.mamh.ssh.demo.service;

import com.mamh.ssh.demo.dao.DepartmentDao;
import com.mamh.ssh.demo.dao.EmployeeDao;
import com.mamh.ssh.demo.entities.Department;
import com.mamh.ssh.demo.entities.Employee;

import java.util.List;

public class DepartmentService {
    private DepartmentDao departmentDao;

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }


    public List<Department> getAll() {
        return departmentDao.getAll();
    }

}
