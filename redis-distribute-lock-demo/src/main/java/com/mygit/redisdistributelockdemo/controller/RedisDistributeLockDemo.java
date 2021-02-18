package com.mygit.redisdistributelockdemo.controller;

import com.mygit.redisdistributelockdemo.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * Description:
 *
 * @author: 邢武彪
 * Date: 2021-02-13
 * Time: 9:45
 */
//@RestController
@Slf4j
@RequestMapping("redis")
public class RedisDistributeLockDemo  {
    /**
     * redis 工具类
     */
    @Autowired
    private RedisUtil util;

    @Autowired
    private RedisTemplate redisTemplate;

    private String key = "store";
    @Value("${server.port}")
    private String port;

    /**
     * 准备工作 , redis 存入初始值
     */
    @RequestMapping("init")
    public String init(@RequestParam(value = "num", required = false, defaultValue = "500") String num) {
        int integer = Integer.parseInt(num);
        boolean store = util.set(key, integer);
        if (store) {
            System.out.println(util.get(key));
            return String.valueOf(util.get(key));
        }
        return "保存失败";
    }

    /**
     * 减1
     */
    @RequestMapping("decr")
    public void decr() {
        String thisThreadId = UUID.randomUUID().toString();
        // 加锁
        redisTemplate.opsForValue().setIfAbsent(key, thisThreadId, 5, TimeUnit.SECONDS);
        /**
         *  synchronized java进程级的锁, 只能保证一个节点的一个Tomcat进程,中的代码块同步
         */
        try {
            synchronized (this) {
                int value = (Integer) util.get(key);
                if (value >= 1) {
                    value = value - 1;
                    util.set(key, value);
                }
               log.info(port + " :当前存量: " + util.get(key));
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }finally {
            // 解锁
            if (thisThreadId.equals(redisTemplate.opsForValue().get(key))){
                redisTemplate.delete(key);
            }
        }
    }


}
