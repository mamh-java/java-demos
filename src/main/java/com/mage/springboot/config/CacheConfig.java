package com.mage.springboot.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


@Configuration
public class CacheConfig {

    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) ->
                method.getName()
                        + "[< "
                        + Arrays.asList(params).toString()
                        + " >]";
    }
}
