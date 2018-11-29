package com.huang.service.impl;

import com.huang.cache.SourceRedisServer;
import com.huang.entity.UserEntity;
import com.huang.mapper.UserMapper;
import com.huang.service.UserService;
import com.huang.util.RedisKeyEnum;
import com.huang.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户操作 serviceImpl
 *
 * @Author huangjihui
 * @Date 2018/9/7 11:03
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    SourceRedisServer sourceRedisServer;

    @Autowired
    UserMapper userMapper;

    @Override
    public UserEntity userGet(UserEntity userEntity) {
        UserEntity user = sourceRedisServer.getObject(RedisKeyUtil.getUserKey(userEntity.getUserName()), UserEntity.class);
        if (user == null) {
            user = userMapper.query(userEntity);
            sourceRedisServer.setObject(RedisKeyUtil.getUserKey(user.getUserName()), user);
        }
        return user;
    }

    @Override
    public void userPut(UserEntity userEntity) {
        int update = userMapper.update(userEntity);
        if (update == 1) {
            UserEntity newEntity = userMapper.query(userEntity);
            sourceRedisServer.setObject(RedisKeyUtil.getUserKey(userEntity.getUserName()), newEntity);
            // test redis hash
            sourceRedisServer.setOneHashObj(RedisKeyEnum.USER_HASH.getValue(), userEntity.getUserName(), newEntity);
        }
    }

    @Override
    public void userPost(UserEntity userEntity) {
        int insert = userMapper.insert(userEntity);
        if (insert == 1) {
            UserEntity newEntity = userMapper.query(userEntity);
            sourceRedisServer.setObject(RedisKeyUtil.getUserKey(userEntity.getUserName()), newEntity);
            // test redis hash
            sourceRedisServer.setOneHashObj(RedisKeyEnum.USER_HASH.getValue(), userEntity.getUserName(), newEntity);
        }
    }

    @Override
    public void userDelete(UserEntity userEntity) {
        int delete = userMapper.delete(userEntity);
        if (delete == 1) {
            sourceRedisServer.delete(RedisKeyUtil.getUserKey(userEntity.getUserName()));
            // test redis hash
            sourceRedisServer.delete(RedisKeyEnum.USER_HASH.getValue());
        }
    }


    @Override
    public void testAOP() {
        System.out.println("user ===========" + Thread.currentThread().getName() + "  " + Thread.currentThread().getId());
    }
}
