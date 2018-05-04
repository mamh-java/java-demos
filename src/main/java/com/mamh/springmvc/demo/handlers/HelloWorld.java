package com.mamh.springmvc.demo.handlers;

import com.mamh.springmvc.demo.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;


@Controller
@RequestMapping("/springmvc")
@SessionAttributes(value = {"user"}, types = {Integer.class,String.class, User.class})
public class HelloWorld {

    @RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(Map<String, Object> map) {
        User user = new User();
        user.setAge(123);
        user.setUsername("Tom");
        user.setEmail("123@mm.com");
        user.setPassword("123456");
        map.put("user", user);
        //默认这个是放到request里面的

        //可以在类上加上 @SessionAttributes(value = {"user"}, types = {Integer.class,String.class, User.class})
        //来把map中对应的类型的,或者对应键值的 对象也放到session中取
        map.put("string1", "xxxxxxxxxxxxxxxxxxx this is a stirng ....");
        map.put("int1", 123321);

        return "success";
    }


    @RequestMapping("/testPOJO")
    private String testPOJO(User user) {
        System.out.println("hellow world.....testPOJO..." + user);
        return "success";
    }

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
