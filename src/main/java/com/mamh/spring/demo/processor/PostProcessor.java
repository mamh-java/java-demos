package com.mamh.spring.demo.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.sound.midi.Soundbank;

public class PostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("postProcessBeforeInitialization: o=" + o);
        System.out.println("postProcessBeforeInitialization: s=" + s);

        return o;       //记得这里一定要返回不能是空的bean
    }

    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("postProcessAfterInitialization: o=" + o);
        System.out.println("postProcessAfterInitialization: s=" + s);


        return o;
    }
}
