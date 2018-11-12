package com.mage.springboot.service;

import com.mage.springboot.entities.Employee;
import com.mage.springboot.mapper.EmployeeMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Cacheable(value = "emp")
    public Employee getEmp(Integer id){
        System.err.println("find emp by id");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }
}
