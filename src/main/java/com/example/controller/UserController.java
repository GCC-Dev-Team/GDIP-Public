package com.example.controller;

import com.example.common.Result;
import com.example.model.dto.*;
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
     * 修改我们自己平台的密码
     * @param userUpdatePasswordRequest
     * @return
     */
    @PutMapping("/passwordInfo")
    public Result updateUserPassword(@RequestBody UserUpdatePasswordRequest userUpdatePasswordRequest){

        return wxuserService.updateUserPassword(userUpdatePasswordRequest);
    }

    /**
     * 修改学习平台的密码
     * @param updateSchoolPasswordRequest
     * @return
     */

    @PutMapping("/schoolPasswordInfo")
    public Result updateSchoolPassword(@RequestBody UpdateSchoolPasswordRequest updateSchoolPasswordRequest){

        return wxuserService.updateSchoolPassword(updateSchoolPasswordRequest);
    }

    /**
     * 学生系统3。0的密码修改接口
     * @param
     * @return
     */

    @PutMapping("/schoolPasswordNewInfo")
    public Result updateSchoolNewPassword(@RequestBody UpdateSchoolPassword3Request updateSchoolPassword3Request){

        return wxuserService.updateSchoolNewPassword(updateSchoolPassword3Request);
    }


    /**
     * 修改/上传头像
     * @param file 头像文件
     * @return
     */
    @PostMapping("/avatar")
    public Result updateAvatar(MultipartFile file){
        return wxuserService.updateAvatar(file);
    }




}
