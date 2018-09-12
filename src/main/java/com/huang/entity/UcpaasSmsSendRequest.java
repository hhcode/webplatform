package com.huang.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 云之讯发送短信请求request
 *
 * @Author huangjihui
 * @Date 2018/9/12 10:10
 */
@ToString
@Component
public class UcpaasSmsSendRequest {

    /**
     * 云之讯平台keyCode
     */

    @Getter
    private String keyCode;

    @Value("${sms.ucpass.keyCode}")
    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    /**
     * 在云之讯平台用户名
     */

    @Getter
    private String userName;

    @Value("${sms.ucpass.userName}")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 回调url
     */

    @Getter
    private String backUrl;

    @Value("${sms.ucpass.backUrl}")
    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    /**
     * 请求uri
     */
    @Getter
    private String serviceUri;

    @Value("${sms.ucpass.serviceUri}")
    public void setServiceUri(String serviceUri) {
        this.serviceUri = serviceUri;
    }

    /**
     * 时间戳
     */
    @Setter
    @Getter
    private Long timeStamp;


    /**
     * iccid
     */
    @Setter
    @Getter
    private String iccid;

    /**
     * 信息内容
     */
    @Setter
    @Getter
    private String content;

    /**
     * 唯一识别码
     */
    @Setter
    @Getter
    private Integer msgId;

    public static String getUriAndParam(UcpaasSmsSendRequest request) {
        StringBuffer sb = new StringBuffer();
        String sign = DigestUtils.md5Hex(request.getKeyCode() + request.getUserName() + request.getTimeStamp());
        sb.append(request.getServiceUri()).append("?keyCode=").append(request.getKeyCode()).append("&user=").append(request.getUserName()).append("&timeTemp=").append(request.getTimeStamp()).append("&sign=").append(sign)
                .append("&iccid=").append(request.getIccid()).append("&content=").append(request.getContent()).append("&msgId=").append(request.getMsgId()).append("&backUrl=").append(request.getBackUrl());
        return sb.toString();
    }

}
