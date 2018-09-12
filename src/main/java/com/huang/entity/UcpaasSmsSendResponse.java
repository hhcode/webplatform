package com.huang.entity;

import lombok.Data;
import lombok.ToString;

/**
 * 云之讯发送短信响应
 * @Author huangjihui
 * @Date 2018/9/12 10:10
 */
@Data
@ToString
public class UcpaasSmsSendResponse {
    /**
     * 结果码
     * 0：成功
     * 1:失败
     * 99：其他错误
     */
    private Integer status;
    /**
     * 结果信息
     */
    private String message;
}
