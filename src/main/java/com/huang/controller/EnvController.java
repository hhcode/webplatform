package com.huang.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Properties;

/**
 * @Author huangjihui
 * @Date 2019/5/6 15:11
 */
@RestController
@Slf4j
@RequestMapping()
public class EnvController {

    @Autowired
    EurekaInstanceConfigBean eurekaInstanceConfigBean;

    @GetMapping("/env")
    @ResponseBody
    public Map<String, String> env() {
        return System.getenv();
    }

    @GetMapping("/prop")
    @ResponseBody
    public Properties prop() {
        return System.getProperties();
    }

    @GetMapping("/eureka")
    @ResponseBody
    public String eureka() {
        return eurekaInstanceConfigBean.getIpAddress() + ":" + eurekaInstanceConfigBean.getNonSecurePort();
    }


}
