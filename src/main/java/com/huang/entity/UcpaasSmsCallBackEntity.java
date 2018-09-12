package com.huang.entity;

import lombok.Data;
import lombok.ToString;

/**
 * 云之讯回短信调数据
 * @Author huangjihui
 * @Date 2018/9/12 10:10
 */
@Data
@ToString
public class UcpaasSmsCallBackEntity {

    /**
     * 短信识别码
     */
    private String from;
    /**
     * 发送短信手机号码
     */
    private String to;
    /**
     * 短信发送时间
     * yyyyMMddHHmmss
     */
    private String time;
    /**
     * 唯一识别码
     */
    private String msgId;
    /**
     * 发送短信内容
     */
    private String content;
}
