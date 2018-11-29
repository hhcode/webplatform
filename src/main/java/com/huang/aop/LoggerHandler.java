package com.huang.aop;

import com.alibaba.fastjson.JSON;
import com.google.gson.annotations.JsonAdapter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * aop 打印日志
 *
 * @Author huangjihui
 * @Date 2018/9/10 11:12
 */
@Aspect
@Component
@Slf4j
public class LoggerHandler {

    @Pointcut("@annotation(com.huang.annotation.HttpLogger)")
    public void httpLogger() {

    }

    @Around("httpLogger()")
    public Object logAround(ProceedingJoinPoint point) throws Throwable {

        long startTimes = System.currentTimeMillis();

        String methodName = point.getSignature().getDeclaringType().getSimpleName() + "." + point.getSignature().getName();
        String args = JSON.toJSONString(point.getArgs());
        log.info("request method : {} args : {}", methodName, args);

        Object result = point.proceed();

        long endTimes = System.currentTimeMillis();

        log.info("response method : {} result : {} span : {}ms", methodName, JSON.toJSONString(result), endTimes - startTimes);

        return result;
    }

}
