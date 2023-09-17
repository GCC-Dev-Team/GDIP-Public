package com.example.utils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ShowPhotoUtil {
    @Resource
    QiniuUtil qiniuUtil;
    public String getPhotoByName(String name) {
      return qiniuUtil.getImageUrl(name);
    }

}
