package com.huang.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author huangjihui
 * @Date 2018/9/5 18:26
 */
@RestController
@RequestMapping("/rest")
public class RestDemo {

    @RequestMapping("/home")
    public String home(){
        return "Hello World !";
    }
}
