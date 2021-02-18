package com.mygit.redisdistributelockdemo.config;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * RedisConfig
 * <p>
 *
 * @author Ruihong, Han
 * @version 1.0
 * @date 2020/5/18
 */
@Configuration
public class RedissonClientConfig {

    /**
     * Redisson
     *
     * @return
     */
//    @Bean
//    public Redisson redisson() {
//        Config config = new Config();
//        // 单机模式   除此之外,还有哨兵模式 等
//        config.useSingleServer().setAddress("redis://localhost:6379").setDatabase(0);
//
//        return (Redisson) Redisson.create(config);
//    }
    @Bean
    public RedissonClient redissonClient() throws IOException {
        // 本例子使用的是yaml格式的配置文件，读取使用Config.fromYAML，如果是Json文件，则使用Config.fromJSON
        Config config = Config.fromYAML(RedissonClientConfig.class.getClassLoader().getResource("redisson-config.yml"));
        return Redisson.create(config);

    }
}