package com.mage.springboot.mapper;


import com.mage.springboot.entities.Employee;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper {

    Employee getEmpById(Integer id);

    int insertEmp(Employee employee);
}
