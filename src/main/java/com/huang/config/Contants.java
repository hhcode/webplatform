package com.huang.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 常量类
 *
 * @Author huangjihui
 * @Date 2018/11/29 17:20
 */
@Component
@Data
public class Contants {
    public static String mybatisLocationResource = "classpath:mapping/*.xml";
    public static String mybatisBasePackage = "com.huang.mapper";

    @Value("${oss.endPoint}")
    private String endPoint;

    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${oss.bucketName}")
    private String bucketName;

    @Value("${oss.envPath}")
    private String envPath;

    @Value("${oss.uploadRootPath}")
    private String uploadRootPath;

    @Value("${oss.domain}")
    private String ossDomain;
}
