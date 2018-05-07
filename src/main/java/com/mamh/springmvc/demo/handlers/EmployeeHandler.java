package com.mamh.springmvc.demo.handlers;

import com.mamh.springmvc.demo.dao.DepartmentDao;
import com.mamh.springmvc.demo.dao.EmployeeDao;
import com.mamh.springmvc.demo.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String input(Map<String, Object> map) {
        System.out.println("input..........");
        map.put("departments", departmentDao.getDepartments());
        map.put("employee", new Employee());

        return "input";
    }


    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String save(Employee employee) {
        System.out.println("save.........." + employee);

        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping("/emps")
    public String list(Map<String, Object> map) {

        map.put("employees", employeeDao.getAll());

        return "list";
    }

}
