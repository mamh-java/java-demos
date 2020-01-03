package com.mamh.design.demo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Singleton2 Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>七月 28, 2019</pre>
 */
public class Singleton2Test {

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
        Singleton2 instance1 = Singleton2.getInstance();
        Singleton2 instance2 = Singleton2.getInstance();
        Assert.assertEquals(instance1, instance2);
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
    }


} 
