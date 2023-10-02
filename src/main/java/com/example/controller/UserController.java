package com.example.controller;

import com.example.common.Result;
import com.example.model.dto.*;
import com.example.service.WxuserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    WxuserService wxuserService;

    /**
     * 获取个人信息的接口
     * @return
     */
    @GetMapping
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
     * 修改教务系统密码
     * @param updateSchoolPasswordRequest
     * @return
     */

    @PutMapping("/schoolPasswordInfo")
    public Result updateSchoolPassword(@RequestBody UpdateSchoolPasswordRequest updateSchoolPasswordRequest){

        return wxuserService.updateSchoolPassword(updateSchoolPasswordRequest);
    }

    /**
     * 查看我的余额
     */
    @GetMapping("/balance")
    public Result getMybalance(){
        return wxuserService.getMybalance();
    }



}
