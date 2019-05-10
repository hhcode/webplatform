package com.huang.service.impl;

import com.aliyun.oss.OSSClient;
import com.huang.config.Contants;
import com.huang.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author huangjihui
 * @Date 2019/5/6 15:00
 */
@Service
@Slf4j
public class OssServiceImpl implements OssService {

    @Autowired
    private Contants contants;

    @Override
    public String uploadFile(String model, String fileName) {
        String objectKey = contants.getEnvPath() + "/" + model + "/" + fileName;
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(contants.getEndPoint(), contants.getAccessKeyId(), contants.getAccessKeySecret());

        // 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。
        ossClient.putObject(contants.getBucketName(), objectKey, new File(contants.getUploadRootPath() + File.separator + model + File.separator + fileName));

        // 关闭OSSClient。
        ossClient.shutdown();
        String fileUrl = contants.getOssDomain() + "/" + objectKey;
        log.info("upload oss success : fileName : {} fileUrl : {}", fileName, fileUrl);
        return fileUrl;
    }

    @Override
    public void deleteFile(String model, String fileName) {

        String objectKey = contants.getEnvPath() + "/" + model + "/" + fileName;

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(contants.getEndPoint(), contants.getAccessKeyId(), contants.getAccessKeySecret());

        // 删除文件。
        ossClient.deleteObject(contants.getBucketName(), objectKey);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Override
    public String upload(MultipartFile file, String model) {
        if (file.isEmpty()) {
            return null;
        }
        String fileName = file.getOriginalFilename();

        File pathFile = new File(contants.getUploadRootPath() + File.separator + model);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        File dest = new File(contants.getUploadRootPath() + File.separator + model + File.separator + fileName);
        try {
            file.transferTo(dest);
            log.info("upload success model: {} fileName : {}", model, fileName);
            return uploadFile(model, fileName);
        } catch (IOException e) {
            log.error(e.toString(), e);
        }
        return null;
    }
}
