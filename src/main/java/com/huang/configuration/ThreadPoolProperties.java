package com.huang.configuration;

import lombok.Data;

/**
 * 线程池相关参数
 *
 * @Author huangjihui
 * @Date 2018/11/15 16:30
 */
@Data
public class ThreadPoolProperties {

    /**
     * 核心线程数
     */
    Integer corePoolSize = 100;

    /**
     * 最大线程池数量
     */
    Integer maxPoolSize = 500;

    /**
     * 队列最大长度
     */
    Integer maxQueueSize = 100;

    /**
     * 线程存活时间
     */
    Long keepAliveSeconds = 60L;

}
