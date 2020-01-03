package com.mamh.design.demo;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Set;


public class SingletonTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getInstance()
     */
    @Test
    public void testGetInstance() throws Exception {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        Assert.assertEquals(instance1, instance2);
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
    }

    @Test
    public void test() {
        Map<String, String> map = System.getenv();
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("=========================");
        System.out.println(map.get("MIUI_SDK_SITE"));
        System.out.println("=========================");
        System.out.println(System.getenv("MIUI_SDK_SITE"));
    }

} 
