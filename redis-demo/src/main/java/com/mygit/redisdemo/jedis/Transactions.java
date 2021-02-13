package com.mygit.redisdemo.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * Description: redis 事务
 *
 * @author: 邢武彪
 * Date: 2021-02-12
 * Time: 8:56
 */
@Deprecated
public class Transactions {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);

        // 开启事务
        Transaction multi = jedis.multi();
        try {
            multi.set("", "");
            multi.set("", "");

            multi.exec();
        }catch (Exception e){
            multi.discard();   // 回滚
            e.printStackTrace();
        }finally {
            jedis.close();
        }
    }
}
