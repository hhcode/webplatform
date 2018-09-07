package com.huang.controller;

import com.huang.entity.UserEntity;
import com.huang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author huangjihui
 * @Date 2018/9/5 18:26
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String home() {
        return "Hello World !";
    }


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public UserEntity userGet(@RequestParam(required=false) String userName, @RequestParam(required=false) String phoneNum) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);
        userEntity.setPhoneNum(phoneNum);
        return userService.userGet(userEntity);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public void userPut(@RequestBody UserEntity userEntity) {
        userService.userPut(userEntity);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void userPost(@RequestBody UserEntity userEntity) {
        userService.userPost(userEntity);
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public void userDelete(@RequestBody UserEntity userEntity) {
        userService.userDelete(userEntity);
    }
}
