package com.mage.springboot.controller;

import com.mage.springboot.entities.Department;
import com.mage.springboot.entities.Employee;
import com.mage.springboot.mapper.DepartmentMapper;
import com.mage.springboot.mapper.EmployeeMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;


    @GetMapping("/department/{id}")
    public Department getDept(@PathVariable("id") Integer id) {
        return departmentMapper.getDeptById(id);
    }

    @GetMapping("/department")
    public Department add(Department department) {
        departmentMapper.insertDept(department);
        return department;
    }

    @GetMapping("/employee/{id}")
    public Employee getEmp(@PathVariable("id") Integer id) {
        return employeeMapper.getEmpById(id);
    }
}
