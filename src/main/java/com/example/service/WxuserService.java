package com.example.service;

import com.example.common.Result;
import com.example.model.dto.*;
import com.example.model.entity.Wxuser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.vo.VerifyVO;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;


/**
* @author L
* @description 针对表【wxuser】的数据库操作Service
* @createDate 2023-07-25 14:59:40
*/
public interface WxuserService extends IService<Wxuser> {


    /**
     * 自己内部调用的方法
     * @return
     */
    /**
     * 获取我的教务系统密码
     * @return
     */
    VerifyVO getMyEducationalAdministrationPassword();
    /**
     * 获取智慧系统3.0的密码
     */
    VerifyVO getMyWisdomSystemPassword();

    /**
     * 控制器的方法
     * @return
     */
    Result getUserInfo();

    Result updateUserNameInfo(@RequestBody UserUpdateNameRequest userUpdateNameRequest);

    Result updateUserPassword(@RequestBody UserUpdatePasswordRequest userUpdatePasswordRequest);

    Result updatePhoneInfo(@RequestBody UserUpdatePhoneRequest userUpdatePhoneRequest);

    Result updateAvatar(@NotNull String photoUrl,String userId);
    Result updateSchoolPassword(@RequestBody UpdateSchoolPasswordRequest updateSchoolPasswordRequest);

}
