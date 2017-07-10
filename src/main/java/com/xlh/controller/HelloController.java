package com.xlh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zhangbin
 * @Type HelloController
 * @Desc
 * @date 2017-07-10
 * @Version V1.0
 */
@Controller
public class HelloController {


    @GetMapping("/demo")
    public String say(){
        return "demo";
    }
}
