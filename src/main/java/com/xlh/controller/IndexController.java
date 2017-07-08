package com.xlh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangbin
 * @Type IndexController
 * @Desc
 * @date 2017-07-08
 * @Version V1.0
 */
@RestController
public class IndexController {
    @GetMapping("/hello")
    public String index() {
        return "hello world,spring boot and jenkins";
    }
}
