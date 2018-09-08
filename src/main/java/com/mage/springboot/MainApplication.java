package com.mage.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * 告诉springboot，来标注一个主程序类，这是一个springboot应用
 */
@SpringBootApplication
//@ImportResource(locations = {"classpath:beans.xml"})//springboot 推荐使用配置类的方式添加组件
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
