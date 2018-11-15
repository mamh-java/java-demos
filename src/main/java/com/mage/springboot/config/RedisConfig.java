package com.mage.springboot.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mage.springboot.entities.Department;
import com.mage.springboot.entities.Employee;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.net.UnknownHostException;

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

    /**
     * 指定一个 处理 Employee 类型的 cacheManager。当有多个缓存管理器的时候需要指定其中一个为主的
     */
    @Bean
    public RedisCacheManager empCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisSerializer<?> redisSerializer = new Jackson2JsonRedisSerializer<>(Employee.class);
        RedisCacheConfiguration defaultCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer));

        RedisCacheManager redisCacheManager = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(defaultCacheConfiguration).build();

        return redisCacheManager;
    }

    /**
     * 指定一个 处理 Department 类型的 cacheManager。当有多个缓存管理器的时候需要指定其中一个为主的
     * 在使用的时候 指定cachemanager是哪个。@CacheConfig(cacheNames = "depts", cacheManager = "deptCacheManager")
     */
    @Bean
    public RedisCacheManager deptCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisSerializer<?> redisSerializer = new Jackson2JsonRedisSerializer<>(Department.class);
        RedisCacheConfiguration defaultCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer));

        RedisCacheManager redisCacheManager = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(defaultCacheConfiguration).build();

        return redisCacheManager;
    }

    /* *
     * 不过上面2种方法都太麻烦了，对于不同的实体类都要指定一个CacheManager。
     * 即如果我的应用里有100种domain对象，那就必须在spring配置文件中配置100个Jackson2JsonRedisSerializer，这显然是不现实的。
     * 所以我们可以使用下面的方式
     */


    /**
     * 这个直接使用 Jackson2JsonRedisSerializer<>(Object.class)  序列化器 在存入redis的时候是可用的，在读取的时候就报错了。
     * 在读取的时候不知道怎么把json字符串 解析为 对应的 对象 中的 字段的。
     * 这里如果只是简单的把  new Jackson2JsonRedisSerializer<>(Object.class) 中泛型类型改为 Object.class 在读取的时候会报错的。
     */
    @Bean
    public RedisCacheManager cacheManager0(RedisConnectionFactory redisConnectionFactory) {
        RedisSerializer<?> redisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        RedisCacheConfiguration defaultCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer));

        RedisCacheManager redisCacheManager = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(defaultCacheConfiguration).build();

        return redisCacheManager;
    }

    /**
     * 应用场景
     * 我们希望通过缓存来减少对关系型数据库的查询次数，减轻数据库压力。在执行DAO类的select***(), query***()方法时，
     * 先从Redis中查询有没有缓存数据，如果有则直接从Redis拿到结果，如果没有再向数据库发起查询请求取数据。
     * <p>
     * 序列化问题
     * 要把domain object做为key-value对保存在redis中，就必须要解决对象的序列化问题。Spring Data Redis给我们提供了一些现成的方案：
     * <p>
     * JdkSerializationRedisSerializer. 使用JDK提供的序列化功能。 优点是反序列化时不需要提供类型信息(class)，
     * 但缺点是序列化后的结果非常庞大，是JSON格式的5倍左右，这样就会消耗redis服务器的大量内存。
     * Jackson2JsonRedisSerializer. 使用Jackson库将对象序列化为JSON字符串。优点是速度快，序列化后的字符串短小精悍。
     * 但缺点也非常致命，那就是此类的构造函数中有一个类型参数，必须提供要序列化对象的类型信息(.class对象)。
     * 通过查看源代码，发现其只在反序列化过程中用到了类型信息。
     * 如果用方案一，就必须付出缓存多占用4倍内存的代价，实在承受不起。如果用方案二，则必须给每一种domain对象都配置一个Serializer，
     * 即如果我的应用里有100种domain对象，那就必须在spring配置文件中配置100个Jackson2JsonRedisSerializer，这显然是不现实的。
     * <p>
     * 通过google, 发现spring data redis项目中有一个#145 pull request（https://github.com/spring-projects/spring-data-redis/pull/145）,
     * 而这个提交请求的内容正是解决Jackson必须提供类型信息的问题。
     * 然而不幸的是这个请求还没有被merge。但我们可以把代码copy一下放到自己的项目中：
     */
    @Bean
    public RedisCacheManager cacheManager1(RedisConnectionFactory redisConnectionFactory) {
        Jackson2JsonRedisSerializer<Object> redisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        redisSerializer.setObjectMapper(objectMapper);

        RedisCacheConfiguration defaultCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer));

        RedisCacheManager redisCacheManager = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(defaultCacheConfiguration).build();

        return redisCacheManager;
    }

    /**
     * 在有多个cachemanager的时候需要把其中一个配置为主的
     */
    @Primary
    @Bean
    public RedisCacheManager cacheManager2(RedisConnectionFactory redisConnectionFactory) {
        GenericJackson2JsonRedisSerializer redisSerializer1 = new GenericJackson2JsonRedisSerializer();

        RedisCacheConfiguration defaultCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer1));

        RedisCacheManager redisCacheManager = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(defaultCacheConfiguration).build();

        return redisCacheManager;
    }

}
