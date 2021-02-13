package com.mygit.ngixdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 * User: 邢武彪
 * Date: 2021-02-11
 * Time: 10:07
 * @author 邢武彪
 */
@RestController
@Slf4j
public class HelloController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/hello")
    public String hello(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info(format.format(new Date())+"项目端口: " + port);
        return format.format(new Date())+"项目端口: " + port;
    }
}
