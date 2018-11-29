package com.huang.cache;

import com.alibaba.fastjson.JSON;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * redis服务抽象父类
 *
 * @Author huangjihui
 * @Date 2018/10/29 18:07
 */
@Component
public abstract class AbstractRedisServer {

    public abstract StringRedisTemplate getStringRedisTemplate();


    public void deleteByKey(String key) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        getStringRedisTemplate().delete(key);
    }

    public void setString(String key, String value) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        getStringRedisTemplate().opsForValue().set(key, value);
    }

    public Set<String> scan(String pattern, Long count) {
        return getStringRedisTemplate().execute(new RedisCallback<Set<String>>() {
            @Override
            public Set<String> doInRedis(RedisConnection connection) throws DataAccessException {
                Set<String> binaryKeys = new HashSet<>();

                Cursor<byte[]> cursor = connection.scan(new ScanOptions.ScanOptionsBuilder().match(pattern).count(count).build());
                while (cursor.hasNext()) {
                    binaryKeys.add(new String(cursor.next()));
                }
                return binaryKeys;
            }
        });
    }

    public String getString(String key) {
        return getStringRedisTemplate().opsForValue().get(key);
    }

    public void setObject(String key, Object value) {
        setString(key, JSON.toJSONString(value));
    }

    public <T> T getObject(String key, Class<T> t) {
        return JSON.parseObject(getString(key), t);
    }

    public void delete(String key) {
        getStringRedisTemplate().delete(key);
    }


    public void setOneHashObj(String key, String mapKey, Object value) {
        getStringRedisTemplate().boundHashOps(key).put(mapKey, JSON.toJSONString(value));
    }

    public <T> T getOneHashObj(String key, String mapKey, Class<T> clazz) {
        String value = (String) getStringRedisTemplate().boundHashOps(key).get(mapKey);
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
        getStringRedisTemplate().boundHashOps(key).putAll(map);
    }

    public void deleteOneHash(String key, String mapKey) {
        getStringRedisTemplate().boundHashOps(key).delete(mapKey);
    }
}
