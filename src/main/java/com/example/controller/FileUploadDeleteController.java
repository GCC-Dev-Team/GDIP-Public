package com.example.controller;

import com.example.common.Result;
import com.example.service.FileUploadDelete;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/fileUpload")
public class FileUploadDeleteController {

    @Resource
    FileUploadDelete fileUploadDelete;
    @PostMapping("/uploadMdForum")
    public String uploadMd(@NotNull MultipartFile file){

        return fileUploadDelete.uploadMd(file,"ForumMd:");
    }
    @DeleteMapping("/photo")
    public Result deletePhotos(String [] fileNames){

        return fileUploadDelete.deletePhotos(fileNames);
    }
    @PostMapping("/uploadPhotoForum")
    public String uploadForumPhoto(@NotNull MultipartFile file) {

        return fileUploadDelete.uploadPhoto(file,"ForumPhoto:");
    }
    @PostMapping("/uploadPhotoProduct")
    public String uploadProductPhoto(@NotNull MultipartFile file) {

        return fileUploadDelete.uploadPhoto(file,"ProductPhoto:");
    }

}
