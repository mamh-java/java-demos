package com.mage.springboot.service;

import com.mage.springboot.entities.Department;
import com.mage.springboot.mapper.DepartmentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "depts", cacheManager = "deptCacheManager")/*可以在类上加个缓存配置的注解，统一设置缓存名称*/
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    @Qualifier("deptCacheManager")  //明确知道使用哪个缓存管理器
            RedisCacheManager deptCacheManager;

    /**
     * cacheNames 和 value 设置缓存名称
     * key 用了设置key的形式,可以使用spel表达式的.key和keyGenerator不能同时使用
     * condition是设置条件,条件满足情况下才进行缓存.
     * unless也是根据条件.进行缓存的.
     *
     * @param id
     * @return
     */
    @Cacheable(key = "#root.methodName + '['+ #id + ']'")
    public Department getDept(Integer id) {
        System.err.println("find dept by id");
        Department department = departmentMapper.getDeptById(id);
        return department;
    }

    /**
     * 使用编码的方式放入缓存,获取缓存等.
     *
     * @param id
     * @return
     */
    public Department getDept1(Integer id) {
        System.err.println("find dept by id");
        Department department = departmentMapper.getDeptById(id);

        //获取某个缓存,然后进行增删改查
        Cache depts = deptCacheManager.getCache("depts");
        depts.put("dept" + id, department);

        return department;
    }

}
