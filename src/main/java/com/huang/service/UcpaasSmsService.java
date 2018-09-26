package com.huang.service;

/**
 * @Author huangjihui
 * @Date 2018/9/11 19:12
 */
public interface UcpaasSmsService {
    boolean sendSms(String iccid, String msg);

    void testAop();
}
