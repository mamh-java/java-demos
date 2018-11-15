package com.mage.springboot.controller;

import com.mage.springboot.dao.DepartmentDao;
import com.mage.springboot.entities.Department;
import com.mage.springboot.service.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentDao departmentDao;


    @Autowired
    DepartmentService departmentService;

    @GetMapping("/department/{id}")
    public Department getDept(@PathVariable("id") Integer id) {
        return departmentService.getDept(id);
    }

    @GetMapping("/department1/{id}")
    public Department getDept1(@PathVariable("id") Integer id) {
        return departmentService.getDept1(id);
    }

    @GetMapping("/emp")
    public String toaddPage(Model model) {
        //查出所有部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "add";//添加员工页面
    }

}
