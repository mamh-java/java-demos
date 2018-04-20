package com.mamh.struts2.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dao {
    private static Map<Integer, Employee> emps = new HashMap<Integer, Employee>();

    static {
        emps.put(1001, new Employee(1001, "mage", "s", "mage@mage.com"));
        emps.put(1002, new Employee(1002, "bright", "ma", "bright@mage.com"));
        emps.put(1003, new Employee(1003, "mamh", "ma", "mamh@mage.com"));
        emps.put(1004, new Employee(1004, "java", "tim", "java@mage.com"));
        emps.put(1005, new Employee(1005, "python2", "ji", "python@mage.com"));
        emps.put(1006, new Employee(1006, "bash", "sh", "bash@mage.com"));
        emps.put(1007, new Employee(1007, "zsh", "zh", "zsh@mage.com"));
        emps.put(1008, new Employee(1008, "python3", "py", "python@mage.com"));
        emps.put(1009, new Employee(1009, "perl", "pl", "perl@mage.com"));
    }

    public List<Employee> getEmployees() {
        return new ArrayList<Employee>(emps.values());
    }


    public void delete(Integer id) {
        emps.remove(id);
    }

    public void save(Employee employee) {
        Set<Integer> integers = emps.keySet();
        Integer max = Collections.max(integers);

        employee.setEmployeeId(max + 1);
        emps.put(employee.getEmployeeId(), employee);
    }


    public Employee get(Integer id) {
        return emps.get(id);
    }


    public void update(Employee employee) {
        emps.put(employee.getEmployeeId(), employee);
    }

}
