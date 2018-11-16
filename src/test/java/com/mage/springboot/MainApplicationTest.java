package com.mage.springboot;

import com.mage.springboot.bean.Person;
import com.mage.springboot.entities.Employee;
import com.mage.springboot.mapper.EmployeeMapper;

import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainApplicationTest {

    @Autowired
    private Person person;

    @Autowired
    private ApplicationContext ioc;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate empRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private JestClient jestClient;

    @Test
    public void testJestClient() throws IOException {
        Employee emp1 = employeeMapper.getEmpById(2);

        Index.Builder builder = new Index.Builder(emp1).index("atguigu").type("emps").id("2");
        Index index = builder.build();
        jestClient.execute(index);

    }

    @Test
    public void testJestClient1() throws IOException {

        String json = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : { \"lastName\" : \"mamh\" }\n" +
                "    }\n" +
                "}";
        Search.Builder builder = new Search.Builder(json).addIndex("atguigu").addType("emps");
        Search search = builder.build();

        SearchResult re = jestClient.execute(search);

        System.err.println(re.getJsonString());

    }

    @Test
    public void testRabbit() {
        //Message message = new Message();
        ///rabbitTemplate.send(message);
        Employee emp = employeeMapper.getEmpById(1);
        String exchange = "exchange.direct";
        String routeKey = "atguigu.news";
        rabbitTemplate.convertAndSend(exchange, routeKey, emp);

        List emps = employeeMapper.getEmps();
        rabbitTemplate.convertAndSend(exchange, routeKey, emps);
    }

    @Test
    public void testRabbit1() {
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.err.println(o);

        o = rabbitTemplate.receiveAndConvert("atguigu.emps");
        System.err.println(o);
    }

    @Test
    public void testRabbit2() {
        Employee emp = employeeMapper.getEmpById(2);
        String exchange = "exchange.direct";
        String routeKey = "atguigu";
        rabbitTemplate.convertAndSend(exchange, routeKey, emp);
    }

    @Test
    public void testRabbit3() {
        //创建一个exchange，名称是 exchange.amqpadmin
        Exchange exchange = new DirectExchange("exchange.amqpadmin");
        amqpAdmin.declareExchange(exchange);
        System.err.println("创建 exchange 完成.");

        //创建一个队列，名称是atguigu.amqpadmin
        amqpAdmin.declareQueue(new Queue("atguigu.amqpadmin", true));

        //绑定
        amqpAdmin.declareBinding(new Binding("atguigu.amqpadmin",
                Binding.DestinationType.QUEUE,
                "exchange.amqpadmin",
                "atguigu.dest", null
        ));
    }

    @Test
    public void testStudent() {
        boolean b = ioc.containsBean("student");
        System.err.println(b);
        System.err.println(ioc.getBean("student"));
    }


    @Test
    public void testPerson() {
        System.err.println(person);
    }

    @Test
    public void testDataSource() throws SQLException {
        System.err.println(dataSource.getClass());
        System.err.println(dataSource.getConnection());
    }

    @Test
    public void testEmployeeMapper() {
        System.err.println("get empby id");
        Employee emp = employeeMapper.getEmpById(1);
        System.err.println(emp);
    }


    @Test
    public void testRedis() {
        stringRedisTemplate.opsForList().leftPush("list", "1");
        stringRedisTemplate.opsForValue().append("msg", "msg01");
        System.err.println(redisTemplate);
        System.err.println(empRedisTemplate);

        Employee emp01 = employeeMapper.getEmpById(1);
        redisTemplate.opsForValue().set("emp01", emp01);  //保存一个对象到redis里面.

        empRedisTemplate.opsForValue().set("emp02", emp01);  //保存一个对象到redis里面.
    }
}