package com.huang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author huangjihui
 * @Date 2018/9/25 18:45
 */
@Component
public class ThreadPoolConfiguration {
    private Integer corePoolSize = 10;
    private Integer maxPoolSize = 50;
    private Integer keepAliveSeconds = 60;
    private Integer maxQueueSize = 100;

    @Bean("executorService")
    public ExecutorService getExecutorService() {
        return new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                keepAliveSeconds, TimeUnit.SECONDS, new LinkedBlockingQueue<>(maxQueueSize));
    }
}
