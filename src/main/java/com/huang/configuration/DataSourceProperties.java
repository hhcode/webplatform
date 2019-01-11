package com.huang.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 数据源参数
 *
 * @Author huangjihui
 * @Date 2018/11/29 17:00
 */
@Data
@Component
public class DataSourceProperties {

    @Value("${mysql.datasource.master.url}")
    private String masterUrl;
    @Value("${mysql.datasource.master.username}")
    private String masterUsername;
    @Value("${mysql.datasource.master.password}")
    private String masterPassword;

    @Value("${mysql.datasource.slave.url}")
    private String slaveUrl;
    @Value("${mysql.datasource.slave.username}")
    private String slaveUsername;
    @Value("${mysql.datasource.slave.password}")
    private String slavePassword;
}
