package com.mygit.redisdistributelockdemo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisLockUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void tryLock(String key,String clientId){

        redisTemplate.opsForValue().setIfAbsent(key, clientId, 10, TimeUnit.SECONDS);

    }
    public void tryUnLock(String key,String clientId){
        Object o = redisTemplate.opsForValue().get(key);
        if (Objects.nonNull(o)&& clientId.equals(o)){
            redisTemplate.delete(key);
        }

    }
}