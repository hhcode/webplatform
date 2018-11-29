package com.huang.configuration;

import lombok.Data;

/**
 * 数据源参数
 *
 * @Author huangjihui
 * @Date 2018/11/29 17:00
 */
@Data
public class DataSourceProperties {
    private String url;
    private String username;
    private String password;
}
