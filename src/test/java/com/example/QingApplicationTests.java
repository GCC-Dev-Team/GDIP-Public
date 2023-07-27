package com.example;

import com.example.mapper.WxuserMapper;
import com.example.service.WxuserService;
import com.example.utils.QiniuUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class QingApplicationTests {
    @Resource
    WxuserService wxuserService;
    @Resource
    WxuserMapper wxuserMapper;

    @Test
    void contextLoads() {
        //System.out.println(wxuserService.getById("qqq"));
       // System.out.println(wxuserMapper.getOneByOpenidWxuser("oc58G4yuVeMwfUiIXp__a4D3YJb4"));
        String fileNameToDelete = "小璇.png";
        String[] fileNameList = {fileNameToDelete};
        QiniuUtil.QiniuCloudDeleteImage(fileNameList);
    }

}
