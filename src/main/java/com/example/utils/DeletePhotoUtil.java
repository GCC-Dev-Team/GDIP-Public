package com.example.utils;
public class DeletePhotoUtil {

    public static Boolean deletePhotoByName(String deletePhotoName){

        String[] fileNameList = {deletePhotoName};

        QiniuUtil.QiniuCloudDeleteImage(fileNameList);

        return true;//删除成功返回ture
    }

    public static Boolean deletePhotos(String[] fileNameList){

        QiniuUtil.QiniuCloudDeleteImage(fileNameList);

        return true;
    }

}
