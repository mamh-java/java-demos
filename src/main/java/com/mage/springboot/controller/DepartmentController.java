package com.mage.springboot.controller;

import com.mage.springboot.entities.Department;
import com.mage.springboot.entities.Employee;
import com.mage.springboot.mapper.DepartmentMapper;
import com.mage.springboot.mapper.EmployeeMapper;
import com.mage.springboot.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    EmployeeService employeeService;

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
        return employeeService.getEmp1(id);
    }


    @GetMapping("/employees")
    public List<Employee> getEmp() {
        return employeeService.getEmps();
    }
}
