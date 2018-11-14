package com.mage.springboot.config;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.util.Assert;

import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RedisConfig {

    @Bean(name = "empRedisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // 改变默认的序列化器,这样就可以把对象以json的形式保存到redis里面了
        RedisSerializer<?> ser = new Jackson2JsonRedisSerializer<>(Object.class);
        template.setDefaultSerializer(ser);
        return template;
    }


    @Bean("customizerInvoker")
    public CacheManagerCustomizers cacheManagerCustomizers() {
        List<CacheManagerCustomizer<CacheManager>> list = new ArrayList<>();
        list.add(new CacheManagerCustomizer<CacheManager>() {
            @Override
            public void customize(CacheManager cacheManager) {
                System.err.println("===============================");
                System.err.println(cacheManager.getCache("1"));
                System.err.println(cacheManager.getCacheNames());


            }
        });
        return new CacheManagerCustomizers(list);
    }


    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisSerializer<?> ser = new Jackson2JsonRedisSerializer<>(Object.class);
        RedisCacheConfiguration defaultCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(ser));

        RedisCacheManager.RedisCacheManagerBuilder builder =
                RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(
                        defaultCacheConfiguration
                );

        return builder.build();
    }


}
