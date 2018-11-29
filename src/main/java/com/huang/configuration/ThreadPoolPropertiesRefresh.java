package com.huang.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池参数修改后立即生效
 *
 * @Author huangjihui
 * @Date 2018/9/19 11:32
 */
@Slf4j
@Component
public class ThreadPoolPropertiesRefresh {

    @Autowired
    private ExecutorService taskExecutorService;

    /**
     * 核心线程数 task
     */
    Integer corePoolSize;

    /**
     * 最大线程池数量 task
     */
    Integer maxPoolSize;

    @Value("${task.thread-pool.core-pool-size}")
    public void setCorePoolSize(Integer corePoolSize) {
        if (this.corePoolSize != null && !corePoolSize.equals(this.corePoolSize)) {
            ThreadPoolExecutor poolExe = ((ThreadPoolExecutor) taskExecutorService);
            poolExe.setCorePoolSize(corePoolSize);
            log.info("update task.thread-pool.core-pool-size {}", poolExe.getCorePoolSize());
        }
        this.corePoolSize = corePoolSize;
    }

    @Value("${task.thread-pool.max-pool-size}")
    public void setMaxPoolSize(Integer maxPoolSize) {
        if (this.maxPoolSize != null && !maxPoolSize.equals(this.maxPoolSize)) {
            ThreadPoolExecutor poolExe = ((ThreadPoolExecutor) taskExecutorService);
            poolExe.setMaximumPoolSize(maxPoolSize);
            log.info("update task.thread-pool.max-pool-size {}", poolExe.getMaximumPoolSize());

        }
        this.maxPoolSize = maxPoolSize;
    }

}
