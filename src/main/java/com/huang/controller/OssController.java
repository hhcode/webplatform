package com.huang.controller;

import com.huang.annotation.HttpLogger;
import com.huang.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author huangjihui
 * @Date 2019/5/6 15:11
 */
@RestController
@Slf4j
@RequestMapping("/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    @HttpLogger
    public String upload(@RequestParam String model, @RequestParam String fileName) {
        return ossService.uploadFile(model, fileName);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @HttpLogger
    public void delete(@RequestParam String model, @RequestParam String fileName) {

        ossService.deleteFile(model, fileName);
    }

    @PostMapping("/uploadVersion")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return ossService.upload(file, request.getParameter("model"));
    }


}
