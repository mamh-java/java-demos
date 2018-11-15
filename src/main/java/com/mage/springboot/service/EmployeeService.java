package com.mage.springboot.service;

import com.mage.springboot.entities.Employee;
import com.mage.springboot.mapper.EmployeeMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "emps", cacheManager = "empCacheManager")/*可以在类上加个缓存配置的注解，统一设置缓存名称*/
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * cacheNames 和 value 设置缓存名称
     * key 用了设置key的形式,可以使用spel表达式的.key和keyGenerator不能同时使用
     * condition是设置条件,条件满足情况下才进行缓存.
     * unless也是根据条件.进行缓存的.
     *
     * @param id
     * @return
     */
    @Cacheable(value = "emps", key = "#root.methodName + '['+ #id + ']' " )
    public Employee getEmp(Integer id) {
        System.err.println("find emp by id");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * keyGenerator 指定自定义的key生成器,我们可以自己定义一个cacheConfig
     * 配置类,KeyGenerator里面配置生成一个这样的bean.
     *
     * @param id
     * @return
     */
    @Cacheable(value = "emps")
    public Employee getEmp1(Integer id) {
        System.err.println("find emp by id");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    @Caching( //定义复杂缓存注解
            cacheable = {@Cacheable(value = "emps", key = "#name")},
            put = {@CachePut(value = "emps", key = "#result.id"), @CachePut(value = "emps", key = "#result.email")}
    )
    public Employee getEmp2(String name) {
        System.err.println("find emp by name");
        return employeeMapper.getEmpByName(name);
    }

    /**
     * value 是设置缓存名称,可以在配置文件中设置spring.cache.cache-names:指定多个值.
     * 这里的value就必须是配置文件中的某一个,不然会报错的.
     * r    *
     *
     * @return
     */
    @Cacheable(value = "emps")
    public List<Employee> getEmps() {
        System.err.println("find all emp");
        List<Employee> emp = employeeMapper.getEmps();
        return emp;
    }

    /**
     * @CachePut 既调用方法也更新缓存数据
     * <p>
     * 测试步骤，
     * 1.查询1号，查到的结果会放到缓存中，其中使用的key是  id。
     * 2.以后查询1号，还是这个缓存中的值
     * 3.更新1号，这个时候因为方法加的有@cachePut注解，也是会 放到缓存中的。不过这个时候使用的key是默认的格式，和上面的格式是不一样的。
     * 4.再次查询1号，得到的是更新前的结果？还是更新后的结果。 答： 更新前的结果，因为2次放入缓存使用的key不一致。不过数据库已经是更新后的结果了。
     * <p>
     * 5. 再次测试，
     */
    @CachePut(value = "emps", key = "#result.id")
    public Employee updateEmp(Employee employee) {
        System.err.println("update emp: " + employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * @param id
     * @return
     * @CacheEvict 删除缓存。
     * allEntries = true 指定清除缓存中的所有数据
     * beforeInvocation = false 默认是false，是否在方法执行之前清除缓存。默认是方法执行后清除缓存的。
     * 如果方法抛出了异常，在方法之后清除缓存的步骤就不执行了。
     */
    @CacheEvict(value = "emps", key = "#id", allEntries = true, beforeInvocation = false)
    public String deleteEmp(Integer id) {
        System.err.println("delete emp: " + id);
        //employeeMapper.deleteEmp(id);
        return null;
    }

    /**
     * 引入redis作为缓存组件
     *
     * 原理: CacheManager(缓存管理器) == Cache 缓存组件来实际给缓存中存取数据.
     *
     */

}
