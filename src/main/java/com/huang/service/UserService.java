package com.huang.service;

import com.huang.entity.UserEntity;

/**
 * 用户操作接口类
 *
 * @Author huangjihui
 * @Date 2018/9/7 11:02
 */
public interface UserService {

    /**
     * get
     *
     * @param userEntity user
     * @return
     */
    UserEntity userGet(UserEntity userEntity);

    /**
     * put
     *
     * @param userEntity user
     */
    void userPut(UserEntity userEntity);

    /**
     * post
     *
     * @param userEntity user
     */
    void userPost(UserEntity userEntity);

    /**
     * delete
     *
     * @param userEntity user
     */
    void userDelete(UserEntity userEntity);

}
