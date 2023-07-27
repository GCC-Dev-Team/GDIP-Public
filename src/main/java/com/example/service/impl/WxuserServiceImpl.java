package com.example.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.model.dto.UserUpdateInfoRequest;
import com.example.model.dto.UserUpdatePasswordRequest;
import com.example.model.entity.Wxuser;
import com.example.model.vo.UserInfoVO;
import com.example.service.WxuserService;
import com.example.mapper.WxuserMapper;
import com.example.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

        if (userInfoVO != null && wxuser != null) {
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
    public Result updateUserInfo(UserUpdateInfoRequest userUpdateInfoRequest) {
        /**
         * 设置改动数字的位置
         */
       int temple=0;

       Wxuser wxuser=AccountHolder.getUser();

       if(userUpdateInfoRequest.getUserName()!=null){

           wxuser.setUserName(userUpdateInfoRequest.getUserName());

           temple=temple+1;
       }

       if(userUpdateInfoRequest.getStudentId()!=null){

           wxuser.setStudentNumber(userUpdateInfoRequest.getStudentId());

           temple=temple+1;
       }
       if(StringUtils.isNotBlank(userUpdateInfoRequest.getPhoneNumber())&& Boolean.TRUE.equals(PhoneNumberRegularExpression.regularPhoneNumberPattern(userUpdateInfoRequest.getPhoneNumber()))){

           wxuser.setPhoneNumber(userUpdateInfoRequest.getPhoneNumber());

           temple=temple+1;
       }

       if(temple>0){
           this.updateById(wxuser);
       }

       return Result.success();

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
    public Result updateAvatar(MultipartFile file) {

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




