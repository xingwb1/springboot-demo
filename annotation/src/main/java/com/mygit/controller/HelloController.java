package com.mygit.controller;

import com.mygit.util.StringFormat;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * k8s健康监测
 * <p>
 *
 * @author Ruihong, Han
 * @version 1.0
 * @date 2020/5/18
 */
@RestController
@Slf4j
public class HelloController {

    @GetMapping("/")
    public String root(@RequestParam(required = false,defaultValue = "111") String param) {
        log.info("执行方法时的打印日志 message ");
        return "返回值";
    }

}
