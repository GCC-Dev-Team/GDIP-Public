package com.example.service;

import com.example.common.Result;
import com.example.model.dto.UserUpdateInfoRequest;
import com.example.model.dto.UserUpdatePasswordRequest;
import com.example.model.entity.Wxuser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.vo.UserInfoVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

/**
* @author L
* @description 针对表【wxuser】的数据库操作Service
* @createDate 2023-07-25 14:59:40
*/
public interface WxuserService extends IService<Wxuser> {

    Result getUserInfo();

    Result updateUserInfo(@RequestBody UserUpdateInfoRequest userUpdateInfoRequest);

    Result updateUserPassword(@RequestBody UserUpdatePasswordRequest userUpdatePasswordRequest);


    Result updateAvatar(MultipartFile file);

}
