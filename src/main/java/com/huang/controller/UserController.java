package com.huang.controller;

import com.huang.annotation.*;
import com.huang.client.ProviderClient;
import com.huang.entity.UserEntity;
import com.huang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutorService;

/**
 * @Author huangjihui
 * @Date 2018/9/5 18:26
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ProviderClient providerClient;

    @Autowired
    ExecutorService taskExecutorService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @HttpLogger
    public String home() {
        return "Hello World !";
    }


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @HttpLogger
    public UserEntity userGet(@RequestParam(required = false) String userName, @RequestParam(required = false) String phoneNum) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);
        userEntity.setPhoneNum(phoneNum);
        return userService.userGet(userEntity);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    @HttpLogger
    public void userPut(@RequestBody UserEntity userEntity) {
        userService.userPut(userEntity);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @HttpLogger
    public void userPost(@RequestBody UserEntity userEntity) {
        userService.userPost(userEntity);
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    @HttpLogger
    public void userDelete(@RequestBody UserEntity userEntity) {
        userService.userDelete(userEntity);
    }

    @GetMapping("/testFeign")
    @HttpLogger
    public String testFeign() {
        String result = providerClient.hello();
        log.info("======== {}", result);
        return result;
    }

    @PostMapping("/testRequest")
    public void testRequest(HttpServletRequest request) {
        log.info(request.getPathInfo());
        log.info(request.getRequestURI());
        log.info(request.getQueryString());
    }

    @GetMapping("/testaop")
    @HttpLogger
    public void testAop(@RequestParam(required = false) String userName) {

        System.out.println(Thread.currentThread().getId() + " " + Thread.currentThread().getName());

        taskExecutorService.execute(() -> {
            userService.testAOP();
        });

    }
}
