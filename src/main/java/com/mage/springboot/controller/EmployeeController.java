package com.mage.springboot.controller;

import com.mage.springboot.dao.DepartmentDao;
import com.mage.springboot.dao.EmployeeDao;
import com.mage.springboot.entities.Department;
import com.mage.springboot.entities.Employee;
import com.mage.springboot.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;


    @Autowired
    EmployeeService employeeService;

    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("emps", all);
        return "list";
    }


    @PutMapping("/emp")
    public String edit(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";//员工列表页面
    }

    @PostMapping("/emp")
    public String add(Employee employee) {//自动将请求参数封装为employee对象
        employeeDao.save(employee);
        return "redirect:/emps";//员工列表页面
    }

    @DeleteMapping("/emp/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        employeeDao.delete(id);
        return "redirect:/emps";//员工列表页面
    }

    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") int id, Model model) {
        Employee employee = employeeDao.get(id);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        model.addAttribute("emp", employee);

        //回到修改页面
        return "edit";
    }


    @GetMapping("/employee/{id}")
    public Employee getEmp(@PathVariable("id") Integer id) {
        return employeeService.getEmp(id);
    }

    @GetMapping("/lastname/{name}")
    public Employee getEmp2(@PathVariable("name") String name) {
        return employeeService.getEmp2(name);
    }

    @GetMapping("/employees")
    public List<Employee> getEmp() {
        return employeeService.getEmps();
    }

    @GetMapping("/employee")
    public Employee update(Employee employee) {
        Employee emp = employeeService.updateEmp(employee);
        return emp;
    }

    @GetMapping("/delemp")
    public String delete(Integer id) {
        return employeeService.deleteEmp(id);
    }
}
