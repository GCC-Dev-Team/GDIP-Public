package com.example.service;

import com.example.common.Result;
import com.example.model.dto.*;
import com.example.model.entity.Wxuser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

/**
* @author L
* @description 针对表【wxuser】的数据库操作Service
* @createDate 2023-07-25 14:59:40
*/
public interface WxuserService extends IService<Wxuser> {

    Result getUserInfo();

    Result updateUserNameInfo(@RequestBody UserUpdateNameRequest userUpdateNameRequest);

    Result updateUserPassword(@RequestBody UserUpdatePasswordRequest userUpdatePasswordRequest);

    Result updatePhoneInfo(@RequestBody UserUpdatePhoneRequest userUpdatePhoneRequest);

    Result updateAvatar(MultipartFile file);

    Result updateSchoolPassword(@RequestBody UpdateSchoolPasswordRequest updateSchoolPasswordRequest);

    Result updateSchoolNewPassword(@RequestBody UpdateSchoolPassword3Request updateSchoolPassword3Request);

}
