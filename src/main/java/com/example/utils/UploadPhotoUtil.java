package com.example.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component

public class UploadPhotoUtil {

    public static String uploadFile(MultipartFile file,String name) {
        try {

            //String originalFileName = file.getOriginalFilename(); // 获取原始文件名
            //String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1); // 获取文件扩展名
           // String newFileName = name + "." + extension; // 根据扩展名生成新的文件名
//// 设置文件保存路径
//            String filePath = "imageActivity/" + newFileName;
           // QiniuUtil.uploadImage(file.getBytes(),newFileName);
            //不适用后缀名
            QiniuUtil.uploadImage(file.getBytes(),name);
            return "文件上传成功！";
        } catch (Exception e) {
            return "文件上传失败：" + e.getMessage();
        }
    }
}
