package com.mage.springboot.config;

import com.mage.springboot.bean.Student;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//指名这是一个配置类，用来添加组件的
public class AppConfig {
    @Bean
    public Student student() {
        return new Student("x", 321);
    }
}
