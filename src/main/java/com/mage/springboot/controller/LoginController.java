package com.mage.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @PostMapping("/usr/login")
    private String login(@RequestParam String username, @RequestParam String password
            , Map<String, Object> map, HttpSession session) {
        if (!StringUtils.isEmpty(username) && password.equals("123456")) {
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";//防止表单重复提交，这里使用重定向,这里的main.html 添加了视图映射了。
            //这样之后 有个问题，就是可以直接通过main.html 直接访问了。跳过了登录操作.
        } else {
            map.put("msg", "用户名密码错误");
            return "index";
        }
    }

}
