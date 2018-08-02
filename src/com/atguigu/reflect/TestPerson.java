package com.atguigu.reflect;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TestPerson implements InvocationHandler {

    @Test
    public void test1() {


        Class<Person> clazz = Person.class;

        try {
            Method m = clazz.getMethod("show");
            m.invoke(m);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
