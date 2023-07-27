package com.example.utils;
import org.springframework.stereotype.Component;
@Component
public class ShowPhotoUtil {
    public static String getPhotoByName(String name) {
      return QiniuUtil.getImageUrl(name);
    }

}
