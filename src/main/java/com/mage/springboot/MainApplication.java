package com.mage.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 告诉springboot，来标注一个主程序类，这是一个springboot应用
 * <p>
 * <p>
 * Cache
 * 缓存接口，定义缓存操作。实现有：RedisCache、EhCacheCache、ConcurrentMapCache等
 * CacheManager
 * 缓存管理器，管理各种缓存（Cache）组件
 *
 * @Cacheable 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存
 * @CacheEvict 清空缓存
 * @CachePut 保证方法被调用，又希望结果被缓存。
 * @EnableCaching 开启基于注解的缓存
 * keyGenerator  缓存数据时key生成策略
 * serialize  缓存数据时value序列化策略
 */
@SpringBootApplication
@MapperScan(value = "com.mage.springboot.mapper")
@EnableCaching
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
