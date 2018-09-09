package com.mage.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

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
}
