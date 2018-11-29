package com.huang.configuration;

import com.huang.entity.BaseThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 线程池配置类
 *
 * @Author huangjihui
 * @Date 2018/11/1 17:27
 */
@Configuration
@Slf4j
public class ThreadPoolConfig {

    /**
     * 处理task请求的线程池
     *
     * @return
     */
    @Bean(name = "taskExecutorService")
    public ExecutorService getCoapExecutorService() {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) getExecutorService(getCoapThreadPoolProperties(), new BaseThreadFactory("Task"));
        executorService.allowCoreThreadTimeOut(true);
        executorService.setRejectedExecutionHandler((r, executor) -> {
            log.error("task pools thread reject ", r);
            if (!executor.isShutdown()) {
                r.run();
            }
        });
        return executorService;
    }

    /**
     * task thread pool 配置
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "task.thread-pool")
    public ThreadPoolProperties getCoapThreadPoolProperties() {
        return new ThreadPoolProperties();
    }


    private ExecutorService getExecutorService(ThreadPoolProperties poolProperties, ThreadFactory factory) {

        Integer maxQueueSize = poolProperties.getMaxQueueSize();
        if (maxQueueSize != null && maxQueueSize > 0) {
            return new ThreadPoolExecutor(poolProperties.getCorePoolSize(), poolProperties.getMaxPoolSize(),
                    poolProperties.getKeepAliveSeconds(), TimeUnit.SECONDS, new LinkedBlockingQueue<>(maxQueueSize), factory);
        }
        return new ThreadPoolExecutor(poolProperties.getCorePoolSize(), poolProperties.getMaxPoolSize(),
                poolProperties.getKeepAliveSeconds(), TimeUnit.SECONDS, new LinkedBlockingQueue<>(), factory);
    }
}
