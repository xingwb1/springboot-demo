package com.mygit.mavenplugindemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:         Jrebel 插件, 快捷键设置为 Ctrl+S rebuild
 *
 * @author: 邢武彪
 * Date: 2021-02-13
 * Time: 10:22
 */
@RestController
public class HelloController {
    @GetMapping
    public String hello(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date())+ "hello 123";
    }
}
