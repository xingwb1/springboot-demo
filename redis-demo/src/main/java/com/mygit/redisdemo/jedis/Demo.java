package com.mygit.redisdemo.jedis;

import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Description:
 * User: 邢武彪
 * Date: 2021-02-12
 * Time: 0:15
 *
 * @author 邢武彪
 */
@Deprecated
public class Demo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        // PONG 
        System.out.println(jedis.ping());


//        System.out.println("清空当前库:" + jedis.flushDB());
//        System.out.println("删除所有数据库中的所有key: " + jedis.flushAll());
        System.out.println("选择库" + jedis.select(0));
//        // 当前库下所有键
        System.out.println("key的数目:" + jedis.dbSize());
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
        System.out.println("键是否存在:" + jedis.exists("username"));
        System.out.println("新增key" + jedis.set("password", "password"));
//        System.out.println("key 类型: " + jedis.type("password"));
//
        System.out.println("取出key: " + jedis.get("password"));
        System.out.println("删除 key " + jedis.del("password"));
//        System.out.println("随机返回key空间的一个: " + jedis.randomKey());
//        System.out.println("重命名key " + jedis.rename("username", "name"));

        jedis.mget("", "");
        jedis.mset("", "", "", "");
        jedis.del("", "", "");

        // 分布式锁, 防止覆盖掉旧值 , 不存在则设置新值
        jedis.setnx("", "");
        // 设置过期时间
        jedis.setex("", 10, "");


        jedis.close();

    }

}
