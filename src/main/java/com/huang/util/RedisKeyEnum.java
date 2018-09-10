package com.huang.util;

import lombok.Getter;

/**
 * redis key enum
 *
 * @Author huangjihui
 * @Date 2018/9/10 10:21
 */
public enum RedisKeyEnum {
    USER_KEY("USER_MSG", "用户key"),
    USER_HASH("USER_HASH", "用户hash");

    @Getter
    private String value;

    @Getter
    private String desc;

    RedisKeyEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
