package com.huang.entity;

import lombok.Data;
import lombok.ToString;

/**
 * 云之讯发送短信请求request
 * @Author huangjihui
 * @Date 2018/9/12 10:10
 */
@Data
@ToString
public class UcpaasSmsSendRequest {

    /**
     * iccid
     */
    private String iccid;
    /**
     * SIM卡号
     */
    private String msisdn;

    /**
     * imsi
     */
    private String imsi;

    /**
     * 信息内容
     */
    private String content;

    /**
     * 唯一识别码
     */
    private Integer msgId;

    /**
     * 回调地址
     */
    private String backUrl;
}
