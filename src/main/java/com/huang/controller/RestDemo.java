package com.huang.controller;

import com.huang.cache.CacheServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author huangjihui
 * @Date 2018/9/5 18:26
 */
@RestController
@RequestMapping("/rest")
@Slf4j
public class RestDemo {

    @Autowired
    CacheServer cacheServer;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "Hello World !";
    }

    @RequestMapping(value = "redis", method = RequestMethod.PUT)
    public void redisPut(@RequestParam("key") @Valid String key, @RequestParam("value") @Valid String value) {
        log.info("key : {} value : {}", key, value);
        cacheServer.setString(key, value);
    }

    @RequestMapping(value = "redis", method = RequestMethod.GET)
    public String redisGet(@RequestParam("key") @Valid String key) {
        return cacheServer.getString(key);
    }

    @RequestMapping(value = "redis", method = RequestMethod.DELETE)
    public void redisDelete(@RequestParam("key") @Valid String key) {
        cacheServer.delete(key);
    }
}
