package com.mygit.redisdemo;

import com.mygit.redisdemo.springboot.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@SpringBootTest
class RedisDemoApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtil util;

    @Test
    void contextLoads() {
//		RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//		connection.flushAll();
//		connection.flushDb();
        redisTemplate.opsForValue().set("aa", "aa");
        System.out.println((String) redisTemplate.opsForValue().get("aa"));
    }

    @Test
    public void redisUtils() {
        HashMap<String, Object> map = new HashMap<>();
		map.put("key", "value");
        util.hmset("hash1", map, 10);


    }
}
