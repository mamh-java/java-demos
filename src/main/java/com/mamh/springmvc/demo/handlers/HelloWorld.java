package com.mamh.springmvc.demo.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/springmvc")
@Controller
public class HelloWorld {


    @RequestMapping("/testCookieValue")
    private String testCookieValue(@CookieValue(value = "JSESSIONID") String c) {
        System.out.println("hellow world.....testCookieValue....CookieValue =  " + c);
        return "success";
    }

    @RequestMapping("/testRequestHeader")
    private String testRequestHeader(@RequestHeader(value = "Accept-Language") String al) {
        System.out.println("hellow world.....testRequestHeader............\"Accept-Language\" = " + al);
        return "success";
    }


    @RequestMapping("/testRequestParam")
    private String testRequestParam(@RequestParam(value = "username") String username,
                                    @RequestParam(value = "password", required = false, defaultValue = "0") String password) {
        System.out.println("hellow world....testRequestParam..............username = " + username + "  password = " + password);
        return "success";
    }


    /**
     * 使用  RequestMapping 来映射请求的 url。
     * 返回值会通过视图解析器来解析为实际的视图。通过 prefix + returnValue + 后缀  得到路径，任何使用转发.
     *
     * @return
     */
    @RequestMapping("/helloworld")
    private String hello() {
        System.out.println("hellow world...................");
        return "success";
    }

    @RequestMapping(value = "/testMethod", method = RequestMethod.POST)
    private String testMethod() {
        System.out.println("hellow world....testMethod...............");
        return "success";
    }


    @RequestMapping(value = "/testParams", params = {"username", "age!=10"},
            headers = {"Accept-Language=zh-CN,en-US;q=0.7,en;q=0.3"})
    private String testParams() {
        System.out.println("hellow world....testParams...............");
        return "success";
    }

    @RequestMapping(value = "/testPath/*/abc")
    private String testPath() {
        System.out.println("hellow world....testPath...............");
        return "success";
    }


    @RequestMapping(value = "/testPathVariable{id}")
    private String testPathVariable(@PathVariable(value = "id") int id) {
        System.out.println("hellow world....PathVariable............... id = " + id);
        return "success";
    }

    @RequestMapping(value = "/testREST/{id}", method = RequestMethod.GET)
    private String testGET(@PathVariable(value = "id") int id) {
        System.out.println("testREST  GET : " + id);
        return "success";
    }

    @RequestMapping(value = "/testREST/{id}", method = RequestMethod.PUT)
    private String testPUT(@PathVariable(value = "id") int id) {
        System.out.println("testREST  PUT : " + id);
        return "success";
    }

    @RequestMapping(value = "/testREST/{id}", method = RequestMethod.DELETE)
    private String testDELETE(@PathVariable(value = "id") int id) {
        System.out.println("testREST  DELETE : " + id);
        return "success";
    }

    @RequestMapping(value = "/testREST", method = RequestMethod.POST)
    private String testPOST() {
        System.out.println("testREST   POST : ");
        return "success";
    }

}
