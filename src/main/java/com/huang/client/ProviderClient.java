package com.huang.client;

import com.huang.client.hystrix.ProviderFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * feign client
 *
 * @Author huangjihui
 * @Date 2018/9/10 15:43
 */
@FeignClient(name = ProviderClient.SERVICE_ID, fallbackFactory = ProviderFallbackFactory.class)
public interface ProviderClient {

    String SERVICE_ID = "PROVIDERDEMO";

    /**
     * provider
     *
     * @return
     */
    @RequestMapping("/hello")
    String hello();
}
