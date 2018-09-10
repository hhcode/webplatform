package com.huang.util;

/**
 * redis key util
 *
 * @Author huangjihui
 * @Date 2018/9/10 10:20
 */
public class RedisKeyUtil {
    /**
     * 以:作为key的分割符
     *
     * @param redisKeyEnum
     * @param keys
     * @return
     */
    public static String getKey(RedisKeyEnum redisKeyEnum, String... keys) {
        String key = redisKeyEnum == null ? "NON" : redisKeyEnum.getValue();
        for (String s : keys) {
            key += ":" + s;
        }
        return key;
    }

    /**
     * 获取存储user的key
     *
     * @param keys
     * @return
     */
    public static String getUserKey(String... keys) {
        return getKey(RedisKeyEnum.USER_KEY, keys);
    }
}
