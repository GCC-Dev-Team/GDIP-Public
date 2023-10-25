package com.example.controller;

import com.example.common.Result;
import com.example.model.dto.*;
import com.example.service.WxuserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 用户
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    WxuserService wxuserService;
    /**
     * 设置或者修改支付密码(默认密码123456)
     */
    @PutMapping("/payPassword")
    public Result updatePayPassword(UpdatePayPasswordRequest updatePayPasswordRequest){
        return wxuserService.updatePayPassword(updatePayPasswordRequest);
    }


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


    /**
     * 查看认证状态(0是申请中，1是已通过申请，2是申请拒绝)
     */
    @GetMapping("/studentCertification")
    public Result getStudentCertificationInfo(){
        return wxuserService.getStudentCertificationInfo();
    }

    /**
     * 认证申请
     */
    @PostMapping("/studentCertification")
    public Result addStudentCertificationInfo(@NotNull String studentIdUrl){
        return wxuserService.addStudentCertificationInfo(studentIdUrl);
    }

    /**
     * 同意/拒绝认证（后台）,code 1表示审核成功通过，2表示拒绝
     */
    @PutMapping("/studentCertification")
    public Result processStudentCertification(@NotNull @RequestBody ProcessStudentCertification processStudentCertification){
        return wxuserService.processStudentCertification(processStudentCertification);
    }




}
