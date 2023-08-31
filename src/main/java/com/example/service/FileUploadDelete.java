package com.example.service;

import com.example.common.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public interface FileUploadDelete {
    String uploadMd(MultipartFile file, String prefix);

    String uploadPhoto(@NotNull MultipartFile file, String prefix);

    Result deletePhotos(String[] fileNames);
}
