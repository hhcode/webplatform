package com.huang.client.hystrix;

import com.huang.client.ProviderClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author huangjihui
 * @Date 2018/9/10 17:49
 */
@Slf4j
@Component
public class ProviderFallbackFactory implements FallbackFactory<ProviderClient> {

    @Override
    public ProviderClient create(Throwable throwable) {
        return new ProviderClient() {
            @Override
            public String hello() {
                log.error("fallbak, cause : {}", throwable.getMessage());
                return "Hello Hystrix !";
            }
        };
    }
}
