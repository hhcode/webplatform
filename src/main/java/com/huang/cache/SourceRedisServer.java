package com.huang.cache;/**
 * @Author huangjihui
 * @Date 2018/11/9 10:55
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 源redis服务类
 *
 * @Author huangjihui
 * @Date 2018/11/9 10:55
 */
@Component("sourceRedisServer")
public class SourceRedisServer extends AbstractRedisServer {

    @Autowired
    private StringRedisTemplate sourceStringRedisTemplate;

    @Override
    public StringRedisTemplate getStringRedisTemplate() {
        return sourceStringRedisTemplate;
    }
}
