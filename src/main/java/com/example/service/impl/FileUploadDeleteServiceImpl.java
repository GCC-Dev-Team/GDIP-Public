package com.example.service.impl;


import com.example.common.Result;
import com.example.model.entity.Wxuser;
import com.example.service.FileUploadDelete;
import com.example.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Service
@Transactional
public class FileUploadDeleteServiceImpl implements FileUploadDelete {

    @Resource
    QiniuUtil qiniuUtil;
    @Resource
    UploadPhotoUtil uploadPhotoUtil;
    @Resource
    ShowPhotoUtil showPhotoUtil;
    @Resource
    DeletePhotoUtil deletePhotos;
    /**
     * 上传文件
     * @param file
     * @param prefix
     * @return
     */
    @Override
    public String uploadMd(MultipartFile file, String prefix) {
        Wxuser user = AccountHolder.getUser();

        String name=prefix+user.getId()+RandomUtil.generateRandomString(32);

        try {
            qiniuUtil.uploadImage(file.getBytes(), name+".txt");

            return qiniuUtil.getImageUrl(name+".txt");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * 上传图片
     * @param file
     * @param prefix
     * @return
     */
    @Override
    public String uploadPhoto(@NotNull MultipartFile file, String prefix) {
        String nameFile = prefix + RandomUtil.generateRandomString(16);

        uploadPhotoUtil.uploadFile(file, nameFile);

        return showPhotoUtil.getPhotoByName(nameFile);
    }

    /**
     * 批量删除图片
     * @param fileNames
     * @return
     */
    @Override
    public Result deletePhotos(String[] fileNames) {
        return Result.success(deletePhotos.deletePhotos(fileNames));
    }
}
