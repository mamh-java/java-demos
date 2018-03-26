package com.mamh.spring.demo.aop.byxml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 验证切面,切面要在ioc容器里面，
 * //把这个类声明为一个切面
 * <p>
 */
public class ValidationAspect {

    /**
     * 定义一个方法，用于声明切面表达式,一般的该方法中不需要其他代码
     */
    public void declareJointPointExpression() {

    }

    /**
     * 前置通知，在目标方法开始执行之前执行
     * <p>
     * 在 ArithmeticCalculator接口的每一个实现类的没一个方法之前都执行
     *
     * @param joinPoint
     */
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("=ValidationAspect=before().................." + methodName + "   args: " + args);
    }

    /**
     * 后置通知，在目标方法执行后执行
     * 无论方法是否执行成功，是否有异常，都会执行这个方法的
     *
     * @param joinPoint
     */
    public void after(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("=ValidationAspect=after()..................." + methodName + "   args: " + args);
    }


    public void afterReturn(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("=ValidationAspect=afterReturn()............." + methodName + "   result: " + result);

    }

    /**
     * @param joinPoint
     * @param ex
     */
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("=ValidationAspect=afterThrowing()..........." + methodName + " exception: " + ex);

    }

//    /**
//     * 必须有返回值，
//     *
//     * @param joinPoint
//     */
//    public Object around(ProceedingJoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().getName();
//        System.out.println("=ValidationAspect=around()..........." + methodName);
//        try {
//            System.out.println(" 前置通知");
//            joinPoint.proceed();
//            System.out.println(" 返回通知");
//        } catch (Throwable throwable) {
//            System.out.println(" 异常通知");
//            //throwable.printStackTrace();
//        }
//        System.out.println(" 后置通知");
//
//        return 100;
//
//    }
}
