package com.mamh.struts2.demo;

import java.util.ArrayList;
import java.util.List;

public class Dao {

    public List<Department> getDepartments() {
        List<Department> list = new ArrayList<Department>();
        list.add(new Department(1001, "aaaa"));
        list.add(new Department(1002, "bbbb"));
        list.add(new Department(1003, "cccc"));
        list.add(new Department(1004, "dddd"));
        list.add(new Department(1005, "eeee"));
        list.add(new Department(1006, "ffff"));
        return list;
    }

    public List<Role> getRoles() {
        List<Role> list = new ArrayList<Role>();
        list.add(new Role(1001, "java"));
        list.add(new Role(1002, "golang"));
        list.add(new Role(1003, "mysql"));
        list.add(new Role(1004, "bash"));
        list.add(new Role(1005, "php"));
        list.add(new Role(1006, "python"));
        list.add(new Role(1007, "perl"));

        return list;
    }


}
