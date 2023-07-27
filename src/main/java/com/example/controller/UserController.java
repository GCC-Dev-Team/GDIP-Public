package com.example.controller;

import com.example.common.Result;
import com.example.model.dto.UserUpdateInfoRequest;
import com.example.model.dto.UserUpdatePasswordRequest;
import com.example.service.WxuserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    WxuserService wxuserService;

    /**
     * 获取个人信息的接口
     * @return
     */
    @GetMapping("/info")
    public Result getUserInfo(){
        return wxuserService.getUserInfo();
    }

    /**
     * 修改普通个人信息的接口
     * @param userUpdateInfoRequest
     * @return
     */
    @PutMapping("/info")
    public Result updateUserInfo(@RequestBody UserUpdateInfoRequest userUpdateInfoRequest){

       return wxuserService.updateUserInfo(userUpdateInfoRequest);
    }

    /**
     * 修改密码
     * @param userUpdatePasswordRequest
     * @return
     */
    @PutMapping("/passwordInfo")
    public Result updateUserPassword(@RequestBody UserUpdatePasswordRequest userUpdatePasswordRequest){

        return wxuserService.updateUserPassword(userUpdatePasswordRequest);
    }


    /**
     * 修改/上传头像
     * @param file 头像文件
     * @return
     */
    @PutMapping("/avatar")
    public Result updateAvatar(MultipartFile file){
        return wxuserService.updateAvatar(file);
    }
}
