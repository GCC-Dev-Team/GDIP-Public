package com.example.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.model.dto.UserUpdateNameRequest;
import com.example.model.dto.UserUpdatePasswordRequest;
import com.example.model.dto.UserUpdatePhoneRequest;
import com.example.model.entity.Wxuser;
import com.example.model.vo.UserInfoVO;
import com.example.service.WxuserService;
import com.example.mapper.WxuserMapper;
import com.example.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @author L
 * @description 针对表【wxuser】的数据库操作Service实现
 * @createDate 2023-07-25 14:59:40
 */
@Service
public class WxuserServiceImpl extends ServiceImpl<WxuserMapper, Wxuser>
        implements WxuserService {
    @Override
    public Result getUserInfo() {

        UserInfoVO userInfoVO = new UserInfoVO();

        Wxuser wxuser = AccountHolder.getUser();

        if (wxuser != null) {
            userInfoVO.setUserName(wxuser.getUserName());

            userInfoVO.setState(wxuser.getState());

            userInfoVO.setAvatar(wxuser.getAvatar());

            userInfoVO.setStudentNumber(wxuser.getStudentNumber());

            userInfoVO.setPhoneNumber(wxuser.getPhoneNumber());

            return Result.success(userInfoVO);

        } else {

            return Result.failure(ResultCode.INTERNAL_ERROR, "没有找到用户");
        }
    }


    @Override
    public Result updateUserNameInfo(UserUpdateNameRequest userUpdateNameRequest) {


       Wxuser wxuser=AccountHolder.getUser();
       //检查用户名是否满足长度的条件
       Boolean temple=CheckStringUtil.checkStringLength(userUpdateNameRequest.getUserName(),2,12);

       if(Boolean.TRUE.equals(temple)){

           wxuser.setUserName(userUpdateNameRequest.getUserName());

           updateById(wxuser);

           return Result.success();
       }

        return Result.failure(ResultCode.PARAM_IS_INVALID);
    }

    @Override
    public Result updatePhoneInfo(UserUpdatePhoneRequest userUpdatePhoneRequest) {

        Wxuser wxuser=AccountHolder.getUser();

        if(StringUtils.isNotBlank(userUpdatePhoneRequest.getPhoneNumber())&&
                Boolean.TRUE.equals(PhoneNumberRegularExpression
                        .regularPhoneNumberPattern(userUpdatePhoneRequest.getPhoneNumber()))){


           wxuser.setPhoneNumber(userUpdatePhoneRequest.getPhoneNumber());

         return Result.success();
       }

        return Result.failure(ResultCode.PARAM_IS_BLANK);
    }

    @Override
    public Result updateUserPassword(UserUpdatePasswordRequest userUpdatePasswordRequest) {
        int temple=0;

        String password = userUpdatePasswordRequest.getPassword();

        String passwordNew = userUpdatePasswordRequest.getPasswordNew();

        String passwordJw = userUpdatePasswordRequest.getPasswordJw();

        Wxuser user = AccountHolder.getUser();

        if(user.getPasswordJw()==null||user.getPasswordJw().equals(userUpdatePasswordRequest.getOldPasswordJw())){

            user.setPasswordJw(passwordJw);

            temple=temple+1;

        }

        if(user.getPassword()==null||user.getPassword().equals(userUpdatePasswordRequest.getOldPassword())){

            user.setPassword(password);

            temple=temple+1;

        }

        if(user.getPasswordNew()==null||user.getPasswordNew().equals(userUpdatePasswordRequest.getOldPasswordNew())){

            user.setPasswordNew(passwordNew);

            temple=temple+1;

        }

        if(temple>0){

            this.updateById(user);
        }

        return Result.success("成功修改"+temple+"处密码");
    }

    @Override
    public Result updateAvatar(@NotNull MultipartFile file) {

        Wxuser user = AccountHolder.getUser();

        String name="avatar"+user.getId();

        if(user.getAvatar()!=null){

            DeletePhotoUtil.deletePhotoByName(name);
        }

        UploadPhotoUtil.uploadFile(file,name);

        user.setAvatar(ShowPhotoUtil.getPhotoByName(name));

        this.updateById(user);

        return Result.success();
    }
}




