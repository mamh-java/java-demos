package com.mage.sssp.repository;

import com.mage.sssp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    Employee getByLastName(String lastName);
}
