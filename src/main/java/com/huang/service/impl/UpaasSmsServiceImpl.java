package com.huang.service.impl;

import com.huang.entity.UcpaasSmsSendRequest;
import com.huang.entity.UcpaasSmsSendResponse;
import com.huang.service.UcpaasSmsService;
import com.huang.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author huangjihui
 * @Date 2018/9/11 19:13
 */
@Slf4j
@Service
public class UpaasSmsServiceImpl implements UcpaasSmsService {

    @Value("${sms.ucpass.keyCode:339bd6f3bb4746189596d3fc639ddadd}")
    private String keyCode;

    @Value("${sms.ucpass.userName:guaishouchongdian}")
    private String userName;

    @Value("${sms.ucpass.backUrl:http://localhost:8081/sms/callback}")
    private String backUrl;

    @Value("${sms.ucpass.serviceUri:http://localhost:8081/openapi/cardsms/sendSMS.do}")
    private String serviceUri;

    @Override
    public boolean sendSms(String iccid, String msg) {

        UcpaasSmsSendRequest request = new UcpaasSmsSendRequest();
        request.setMsisdn(iccid);
        request.setContent(msg);
        request.setMsgId(RandomUtil.getNextLong(12));
        request.setBackUrl(backUrl);

        String uri = serviceUri + getSysParam();
        log.debug("send msg uri : {} request : {}", uri, request.toString());

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<UcpaasSmsSendRequest> formEntity = new HttpEntity<>(request, headers);
            ResponseEntity<UcpaasSmsSendResponse> resp = restTemplate.postForEntity(uri, formEntity, UcpaasSmsSendResponse.class);

            log.debug("snd sms return {}", resp.getBody());
            if (resp.getBody().getStatus() == 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("send sms error. platform : ucpaas errorMsg : {}", e);
        }

        return false;
    }

    /**
     * 获取系统级参数
     *
     * @return
     */
    private String getSysParam() {
        StringBuffer sb = new StringBuffer();
        long timeStamp = System.currentTimeMillis();
        String sign = DigestUtils.md5Hex(keyCode + userName + timeStamp);
        sb.append("?keyCode=").append(keyCode).append("&timeTemp=").append(timeStamp).append("&user=").append(userName).append("&sign=").append(sign);
        return sb.toString();
    }


}
