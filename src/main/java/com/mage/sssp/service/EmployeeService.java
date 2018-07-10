package com.mage.sssp.service;

import com.mage.sssp.entity.Employee;
import com.mage.sssp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public Page<Employee> getPage(int pageNo, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize);

        Page<Employee> all = employeeRepository.findAll(pageRequest);


        return all;
    }


    @Transactional(readOnly = true)
    public Employee getByLastName(String lastName) {

        Employee employee = employeeRepository.getByLastName(lastName);
        return employee;
    }


    @Transactional
    public void saveEmployee(Employee employee) {
        employee.setCreateTime(new Date());
        employeeRepository.saveAndFlush(employee);
    }

    @Transactional(readOnly = true)
    public Employee get(Integer id) {
        return employeeRepository.getOne(id);
    }
}
