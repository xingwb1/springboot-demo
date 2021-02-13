package com.mygit.springbootcrontab.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:     本地单机 定时
 * User: 邢武彪
 * Date: 2021-02-09
 * Time: 17:05
 *
 * @author 邢武彪
 */
@EnableScheduling
@Configuration
@Slf4j
public class DistributeCronJobDemo {
    @Value("${server.port}")
    private String port;

    final private String cron2 = "0/2 * * * * *";

    private final String CRON_TIME_AT_1_AM = "0 0 1 * * ? ";

    @Scheduled(cron = cron2)
    public void job() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info(format.format(new Date()) + "项目端口: " + port);
    }
}
