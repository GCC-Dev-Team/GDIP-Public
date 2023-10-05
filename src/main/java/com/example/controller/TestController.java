package com.example.controller;

import com.example.anno.NoNeedLogin;
import com.example.mapper.WxuserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    WxuserMapper wxuserMapper;
    @NoNeedLogin
    @GetMapping("/get")
    public String get(String str1){
        System.out.println("我是一号不需要登录，但是有拦截器，拦截器再判断!");
        return wxuserMapper.getOneByOpenidWxuser(str1).toString();
    }

    @GetMapping("/g")
    public String get2(String str1){
        System.out.println("我是二号不需要登录,拦截器配置没有!");
        return wxuserMapper.getOneByOpenidWxuser(str1).toString();
    }
    @GetMapping("/g2")
    public String get3(String str1){
        System.out.println("我是3号需要登录,拦截器配置you,但是token需要，没有用token");
        return wxuserMapper.getOneByOpenidWxuser(str1).toString();
    }

}
