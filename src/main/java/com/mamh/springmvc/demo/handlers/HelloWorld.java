package com.mamh.springmvc.demo.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {


    /**
     * 使用  RequestMapping 来映射请求的 url。
     * 返回值会通过视图解析器来解析为实际的视图。通过 prefix + returnValue + 后缀  得到路径，任何使用转发.
     * @return
     */
    @RequestMapping("/helloworld")
    private String hello() {

        System.out.println("hellow world...................");

        return "success";
    }
}
