package com.mage.springboot.config;

import com.mage.springboot.bean.Student;
import com.mage.springboot.resolver.LocalResolver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration//指名这是一个配置类，用来添加组件的
public class AppConfig {
    @Bean
    public Student student() {
        return new Student("x", 321);
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurerAdapter() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/index.html").setViewName("index");
            }
        };
        return webMvcConfigurer;
    }

    @Bean
    public LocaleResolver localeResolver() {
        LocalResolver localResolver = new LocalResolver();
        return localResolver;
    }
}
