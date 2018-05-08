package com.mamh.springmvc.demo.converters;

import com.mamh.springmvc.demo.entities.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter implements Converter<String, Employee> {
    @Nullable
    @Override
    public Employee convert(String source) {
        System.out.println("convert = " + source);

        /**
         * Employee [
         * id=null, lastName=, email=, gender=null,
         * department=Department [id=101, departmentName=null],
         * birth=Thu Dec 06 00:00:00 CST 1990, salary=null
         * ]
         */


        return new Employee();
    }
}
