package com.mygit.controller.other;

import org.springframework.web.bind.annotation.GetMapping;
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
public class RootController {


    @GetMapping("/ok")
    public String ok() {
        return "ok";
    }

}
