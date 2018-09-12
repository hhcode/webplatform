package com.huang.entity;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 云之讯发送短信请求request
 *
 * @Author huangjihui
 * @Date 2018/9/12 10:10
 */
@Data
@ToString
@Component
public class UcpaasSmsSendRequest {

    /**
     * 云之讯平台keyCode
     */
    @Value("${sms.ucpass.keyCode}")
    private String keyCode;

    /**
     * 在云之讯平台用户名
     */
    @Value("${sms.ucpass.userName}")
    private String userName;

    /**
     * 回调url
     */
    @Value("${sms.ucpass.backUrl}")
    private String backUrl;

    /**
     * 请求uri
     */
    @Value("${sms.ucpass.serviceUri}")
    private String serviceUri;

    /**
     * 时间戳
     */
    private Long timeStamp;


    /**
     * iccid
     */
    private String iccid;

    /**
     * 信息内容
     */
    private String content;

    /**
     * 唯一识别码
     */
    private Integer msgId;

    public static String getUriAndParam(UcpaasSmsSendRequest request) {
        StringBuffer sb = new StringBuffer();
        String sign = DigestUtils.md5Hex(request.getKeyCode() + request.getUserName() + request.getTimeStamp());
        sb.append(request.getServiceUri()).append("?keyCode=").append(request.getKeyCode()).append("&user=").append(request.getUserName()).append("&timeTemp=").append(request.getTimeStamp()).append("&sign=").append(sign)
                .append("&iccid=").append(request.getIccid()).append("&content=").append(request.getContent()).append("&msgId=").append(request.getMsgId()).append("&backUrl=").append(request.getBackUrl());
        return sb.toString();
    }

}
