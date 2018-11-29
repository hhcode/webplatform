package com.huang.configuration;

import lombok.Data;

/**
 * redis配置参数
 *
 * @Author huangjihui
 * @Date 2018/11/9 10:36
 */
@Data
public class RedisProperties {
    private String host;
    private Integer port;
    private String password;
    private Integer databaseIndex;
}
