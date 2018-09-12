package com.huang.service.impl;

import com.alibaba.fastjson.JSON;
import com.huang.entity.UcpaasSmsSendRequest;
import com.huang.entity.UcpaasSmsSendResponse;
import com.huang.service.UcpaasSmsService;
import com.huang.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
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


    @Override
    public boolean sendSms(String iccid, String content) {

        UcpaasSmsSendRequest request = new UcpaasSmsSendRequest();
        request.setTimeStamp(System.currentTimeMillis());
        request.setIccid(iccid);
        request.setContent(content);
        request.setMsgId(RandomUtil.getNextInt(8));

        String uri = UcpaasSmsSendRequest.getUriAndParam(request);
        log.info("send msg uri : {} ", uri);

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> resp = restTemplate.postForEntity(uri, null, String.class);

            UcpaasSmsSendResponse smsResp = JSON.parseObject(resp.getBody(), UcpaasSmsSendResponse.class);

            log.info("snd sms return {}", resp.getBody());
            if (smsResp.getStatus() == 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("send sms error. platform : ucpaas errorMsg : {}", e);
        }

        return false;
    }


    public static void main(String[] args) {

        long timeStamp = System.currentTimeMillis();
        String code = "339bd6f3bb4746189596d3fc639ddaddguaishouchongdian" + timeStamp;
        String sign = DigestUtils.md5Hex(code);
        System.out.println(code);
        System.out.println(timeStamp);
        System.out.println(sign);
    }

}
