package com.huang.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author huangjihui
 * @Date 2018/9/26 16:42
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.context = applicationContext;
    }

    public static <T> T getBeanByType(Class<T> t) {
        Map<String, T> map = context.getBeansOfType(t);
        return map.entrySet().iterator().next().getValue();
    }
}
