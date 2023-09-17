package com.example.utils;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
public class DeletePhotoUtil {

    @Resource
    QiniuUtil qiniuUtil;
    public  Boolean deletePhotos(String[] fileNameList){

        qiniuUtil.quinineCloudDeleteImage(fileNameList);

        return true;
    }

}
