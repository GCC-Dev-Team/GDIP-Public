package com.example;

import com.example.service.WxuserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class QingApplicationTests {
    @Resource
    WxuserService wxuserService;

    @Test
    void contextLoads() {
        System.out.println(wxuserService.getById("qqq"));
    }

}
