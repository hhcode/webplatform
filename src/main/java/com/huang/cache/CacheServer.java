package com.huang.cache;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

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


    public void setOneHashObj(String key, String mapKey, Object value) {
        stringRedisTemplate.boundHashOps(key).put(mapKey, JSON.toJSONString(value));
    }

    public <T> T getOneHashObj(String key, String mapKey, Class<T> clazz) {
        String value = (String) stringRedisTemplate.boundHashOps(key).get(mapKey);
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        if (String.class.getName().equals(clazz.getName())) {
            return (T) value;
        }
        return JSON.parseObject(value, clazz);
    }

    public <K, V> Map<K, V> getHash(String key) {
        String value = getString(key);
        return StringUtils.isEmpty(value) ? null : JSON.parseObject(value, Map.class);
    }

    public void setHash(String key, Map<String, Object> map) {
        stringRedisTemplate.boundHashOps(key).putAll(map);
    }

    public void deleteOneHash(String key, String mapKey) {
        stringRedisTemplate.boundHashOps(key).delete(mapKey);
    }
}
