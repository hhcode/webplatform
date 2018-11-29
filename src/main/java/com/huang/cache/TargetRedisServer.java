package com.huang.cache;/**
 * @Author huangjihui
 * @Date 2018/11/9 10:55
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 目标redis服务类
 *
 * @Author huangjihui
 * @Date 2018/11/9 10:55
 */
@Component("targetRedisServer")
public class TargetRedisServer extends AbstractRedisServer {
    @Autowired
    private StringRedisTemplate targetStringRedisTemplate;

    @Override
    public StringRedisTemplate getStringRedisTemplate() {
        return targetStringRedisTemplate;
    }
}
