package com.mamh.spring.demo.aop.byannotation;

import org.springframework.stereotype.Component;

/**
 * 这个不能加到接口那个上面。
 */
@Component
public class ArithmeticCalculatorImpl implements ArithmeticCalculator {
    public int add(int i, int j) {
        return i + j;
    }

    public int sub(int i, int j) {
        return i - j;
    }

    public int mul(int i, int j) {
        return i * j;
    }

    public int div(int i, int j) {
        return i / j;
    }
}
