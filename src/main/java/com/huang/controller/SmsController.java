package com.huang.controller;

import com.huang.entity.UcpaasSmsCallBackEntity;
import com.huang.entity.UcpaasSmsSendRequest;
import com.huang.entity.UcpaasSmsSendResponse;
import com.huang.service.UcpaasSmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author huangjihui
 * @Date 2018/9/11 19:19
 */
@RestController
@Slf4j
public class SmsController {
    @Autowired
    private UcpaasSmsService ucpaasSmsService;

    @RequestMapping("/sms/sendSms")
    public boolean sendSms(@RequestParam("iccid") String iccid, @RequestParam("msg") String msg) {
        log.info("sendSms  iccid : {}, msg : {}", iccid, msg);
        return ucpaasSmsService.sendSms(iccid, msg);
    }

    @RequestMapping("/sms/callback")
    public void callBack(@RequestBody UcpaasSmsCallBackEntity ucpaasSmsCallBackEntity) {
        log.info("callback msg : {}", ucpaasSmsCallBackEntity);
    }

    @RequestMapping("/sms/callback1")
    public void callBack1(@RequestParam(name = "from", required = false) String from, @RequestParam(name = "to", required = false) String to, @RequestParam(name = "time", required = false) String time, @RequestParam(name = "state", required = false) String state, @RequestParam(name = "msgId", required = false) String msgId, @RequestParam(name = "content", required = false) String content) {
        log.info("callback from : {} to : {} time : {} state : {} msgId : {} content : {}", from, to, time, state, msgId, content);
    }

    @RequestMapping("/sms/callback2")
    public void callBack2(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        StringBuffer sb = new StringBuffer();
        map.entrySet().forEach(entry -> {
            sb.append(entry.getKey()).append(" : ").append(entry.getValue()).append(" ");
        });
        log.info("callback msg {} ", sb.toString());
    }


    @RequestMapping("/openapi/cardsms/sendSMS.do")
    public UcpaasSmsSendResponse testSendSms(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();

        log.info("===== sendSms msg : {}", params);

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            UcpaasSmsCallBackEntity callBackEntity = new UcpaasSmsCallBackEntity();
            callBackEntity.setFrom("from123456");
            callBackEntity.setContent(params.get("content")[0]);
            callBackEntity.setTo(params.get("iccid")[0]);
            callBackEntity.setMsgId(Integer.valueOf(params.get("msgId")[0]));
            callBackEntity.setTime(String.valueOf(System.currentTimeMillis()));

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<UcpaasSmsCallBackEntity> formEntity = new HttpEntity<>(callBackEntity, headers);
            restTemplate.postForEntity("http://localhost:8081/sms/callback", formEntity, String.class);
        }).start();


        UcpaasSmsSendResponse resp = new UcpaasSmsSendResponse();
        resp.setMessage("sendmsg success...");
        resp.setStatus(0);
        return resp;
    }
}
