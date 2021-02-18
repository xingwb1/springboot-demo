package com.mygit.redisdistributelockdemo.controller;

import com.mygit.redisdistributelockdemo.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


/**
 * Description:
 *
 * @author: 邢武彪
 * Date: 2021-02-13
 * Time: 9:45
 */
@RestController
@RequestMapping("redis")
@Slf4j
public class RedissionDemo {
    /**
     * redis 工具类
     */
    @Autowired
    private RedisUtil util;
    /**
     * redis redission 客户端
     */
    @Autowired
    private RedissonClient redisson;
    private String key = "storekey";
    @Value("${server.port}")
    private String port;

    /**
     * 准备工作 , redis 存入初始值
     */
    @RequestMapping("init")
    public String init(@RequestParam(value = "num", required = false, defaultValue = "500") String num) {
        boolean store = util.set(key, Integer.parseInt(num));
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
        /**
         * 加锁
         *  WRONGTYPE Operation against a key holding the wrong kind of value; nested exception
         *  is redis.clients.jedis.exceptions.JedisDataException:
         *  WRONGTYPE Operation against a key holding the wrong kind of value
         *
         *  init 初始化已经用到了storekey, set(key,Object) , getLock 参数来个没用过的key
         */

        RLock lock = redisson.getLock("otherKey");
        lock.lock(10, TimeUnit.SECONDS);

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
                System.out.println(port + " :当前存量: " + util.get(key));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    @RequestMapping("hello")
    public String hello() {
        return String.valueOf(System.currentTimeMillis());
    }
}
