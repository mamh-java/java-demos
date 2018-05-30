package com.mamh.springmvc.demo.handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandler {


    /**
     * 方法的入参中可以加入exception类型的参数，该参数对应了发生的异常对象。入参中不能传入map。
     * 需要使用modelAndView作为返回值。
     *
     * @param e
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler({ArithmeticException.class})
    public ModelAndView handleException(Exception e) {
        System.out.println("public class ExceptionHandler {\n出异常了：" + e);
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception", e);
        return modelAndView;
    }
}
