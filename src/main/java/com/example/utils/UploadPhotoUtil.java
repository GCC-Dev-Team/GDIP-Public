package com.example.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Component
public class UploadPhotoUtil {
    @Resource
    QiniuUtil qiniuUtil;

    public void uploadFile(@NotNull MultipartFile file, @NotNull String name) {
        try {
            qiniuUtil.uploadImage(file.getBytes(),name);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
