package com.huang.cache;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author huangjihui
 * @Date 2018/9/6 15:52
 */
@Component
public class CacheServer {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void setObject(String key, Object value) {
        setString(key, JSON.toJSONString(value));
    }

    public <T> T getObject(String key, Class<T> t) {
        return JSON.parseObject(getString(key), t);
    }

    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }
}
