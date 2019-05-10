package com.huang.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author huangjihui
 * @Date 2019/5/6 15:00
 */
public interface OssService {

    /**
     * 上传文件
     *
     * @param model
     * @param fileName
     * @return
     */
    String uploadFile(String model, String fileName);

    /**
     * 删除文件
     *
     * @param model
     * @param fileName
     */
    void deleteFile(String model, String fileName);

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    String upload(MultipartFile file,String model);
}
