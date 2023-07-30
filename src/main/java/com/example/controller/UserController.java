package com.example.controller;

import com.example.common.Result;
import com.example.model.dto.UserUpdateNameRequest;
import com.example.model.dto.UserUpdatePasswordRequest;
import com.example.model.dto.UserUpdatePhoneRequest;
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
     * 修改昵称的接口
     * @param userUpdateNameRequest
     * @return
     */
    @PutMapping("/nameInfo")
    public Result updateUserNameInfo(@RequestBody UserUpdateNameRequest userUpdateNameRequest){

       return wxuserService.updateUserNameInfo(userUpdateNameRequest);
    }
    @PutMapping("/phoneInfo")
    public Result updatePhoneInfo(@RequestBody UserUpdatePhoneRequest userUpdatePhoneRequest){

        return wxuserService.updatePhoneInfo(userUpdatePhoneRequest);
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
