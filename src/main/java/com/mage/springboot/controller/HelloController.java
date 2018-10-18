package com.mage.springboot.controller;

import com.mage.springboot.bean.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String name;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello mage 马哥私房菜 淘宝店铺 https://shop592330910.taobao.com/";
    }

    @RequestMapping("/say")
    @ResponseBody
    public String say() {
        return "hello  " + name;
    }

    @RequestMapping("/success")
    public String success(Map<String, Object> map) {
        map.put("name", "<h1>你好你好</h1>");
        map.put("user", Arrays.asList("张三", "李四", "王五"));
        map.put("student", Arrays.asList(new Student("张三", 12),
                new Student("王五", 34),
                new Student("李四", 56)));
        return "success";
    }

    @RequestMapping("/query")
    @ResponseBody
    public Map<String, Object> jdbc() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from sssp_department");
        return list.get(0);
    }
}
