package com.mage.sssp.handler;

import com.mage.sssp.entity.Employee;
import com.mage.sssp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeService employeeService;


    @RequestMapping("/emps")
    public String list(@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNo, Map<String, Object> map) {

        int pageNumber = 1;
        try {
            pageNumber = Integer.parseInt(pageNo);

            if (pageNumber < 1) {
                pageNumber = 1;
            }
        } catch (Exception e) {

        }

        Page<Employee> page = employeeService.getPage(pageNumber, 5);
        map.put("page", page);

        return "emp/list";
    }


}
