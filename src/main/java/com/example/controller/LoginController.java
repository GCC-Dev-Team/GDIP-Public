package com.example.controller;

import com.example.common.Result;
import com.example.model.dto.WxLoginRequest;
import com.example.service.WxLoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    WxLoginService wxLoginService;

    @PostMapping("/")
    public Result wxLogin(@RequestBody WxLoginRequest wxLoginRequest) {

        return wxLoginService.wxLogin(wxLoginRequest);
    }

}
