package com.mamh.redis.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTest {
    private JedisPool pool;
    private Jedis jedis;

    @Before
    public void before() {
        pool = new JedisPool();
        jedis = pool.getResource();



    }

    @Test
    public void testPing() {
        String ping = jedis.ping();
        Assert.assertEquals("PONG", ping);


     }




}
