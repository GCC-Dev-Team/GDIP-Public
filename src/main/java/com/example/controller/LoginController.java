package com.example.controller;

import com.example.anno.NoNeedLogin;
import com.example.common.Result;
import com.example.model.dto.WxLoginRequest;
import com.example.service.WxLoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 登录
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    WxLoginService wxLoginService;

    @PostMapping("/wx")
    @NoNeedLogin
    public Result wxLogin(@NotNull @RequestBody WxLoginRequest wxLoginRequest) {

        return wxLoginService.wxLogin(wxLoginRequest);
    }

}
