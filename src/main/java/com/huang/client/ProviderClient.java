package com.huang.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * feign client
 * @Author huangjihui
 * @Date 2018/9/10 15:43
 */
@FeignClient("PROVIDERDEMO")
public interface ProviderClient {

    /**
     * provider
     * @return
     */
    @RequestMapping("/hello")
    String hello();
}
