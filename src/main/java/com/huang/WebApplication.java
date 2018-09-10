package com.huang;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * 启动类
 *
 * @SpringBootApplication springboot
 * @EnableApolloConfig apollo 配置中心
 * @EnableDiscoveryClient eureka 服务注册与发现
 * @EnableFeignClients feign 远程调用 Ribbon 负载均衡
 * @EnableHystrix hystrix 断路器
 * @Author hjhuuuu@126.com
 * @Date 2018/9/5 18:25
 */
@EnableApolloConfig
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
