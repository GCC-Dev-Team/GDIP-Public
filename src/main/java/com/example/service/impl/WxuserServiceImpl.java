package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.mapper.StudentAuditsMapper;
import com.example.model.dto.*;
import com.example.model.entity.StudentAudits;
import com.example.model.entity.Wxuser;
import com.example.model.vo.StudentAuditsVO;
import com.example.model.vo.UserInfoVO;
import com.example.model.vo.VerifyVO;
import com.example.service.AccountJudgmentService;
import com.example.service.WxuserService;
import com.example.mapper.WxuserMapper;
import com.example.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author L
 * @description 针对表【wxuser】的数据库操作Service实现
 * @createDate 2023-07-25 14:59:40
 */
@Service
@Transactional
public class WxuserServiceImpl extends ServiceImpl<WxuserMapper, Wxuser>
        implements WxuserService {
    @Resource
    AccountJudgmentService accountJudgmentService;

    @Resource
    StudentAuditsMapper studentAuditsMapper;
    @Override
    public Result getUserInfo() {

        UserInfoVO userInfoVO = new UserInfoVO();

        Wxuser wxuser = AccountHolder.getUser();

        if (wxuser != null) {

            BeanUtils.copyProperties(wxuser,userInfoVO);

            userInfoVO.setIsBindSystemEdu(wxuser.getState().equals(1));//如果是1表示已经认证呢

            return Result.success(userInfoVO);

        } else {

            return Result.failure(ResultCode.USER_ERROR_NULL);
        }
    }

    @Override
    public Result updatePayPassword(UpdatePayPasswordRequest updatePayPasswordRequest) {
        Wxuser user = AccountHolder.getUser();

        if (user.getPasswordPay().equals(updatePayPasswordRequest.getOldPassword())){

            user.setPasswordPay(updatePayPasswordRequest.getNewPassword());

            updateById(user);

            return Result.success("成功修改密码!");
        }

        return  Result.failure(ResultCode.SYSTEM_ERROR,"旧密码错误");

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

           this.updateById(wxuser);

         return Result.success();
       }

        return Result.failure(ResultCode.PARAM_IS_BLANK);
    }

    @Override
    public Result updateUserPassword(@NotNull UserUpdatePasswordRequest userUpdatePasswordRequest) {

        String password = userUpdatePasswordRequest.getPassword();

        Wxuser user = AccountHolder.getUser();

        if(user.getPassword()==null||user.getPassword().equals(userUpdatePasswordRequest.getOldPassword())){

            user.setPassword(password);

            this.updateById(user);

            return Result.success(ResultCode.USER_SUCCESS_UPDATE_PASSWORD);

        }

        return Result.failure(ResultCode.USER_ERROR_UPDATE_PASSWORD);

    }

    @Override
    public Result updateSchoolPassword(@NotNull UpdateSchoolPasswordRequest updateSchoolPasswordRequest) {

        String passwordJw = updateSchoolPasswordRequest.getPasswordJw();

        String studentId=updateSchoolPasswordRequest.getStudentId();

        Wxuser user = AccountHolder.getUser();

        if(Boolean.TRUE.equals(accountJudgmentService.judgeIsAccount(updateSchoolPasswordRequest.getStudentId(),
                                updateSchoolPasswordRequest.getPasswordJw()))){
            user.setPasswordJw(passwordJw);
            user.setStudentNumber(updateSchoolPasswordRequest.getStudentId());
            user.setState(1);
            this.updateById(user);
            return Result.success(updateSchoolPasswordRequest.getStudentId());
        }
        return Result.failure(ResultCode.USER_ERROR_UPDATE_SCHOOL_PASSWORD);
    }


    @Override
    public Result updateAvatar(String photoUrl, String userId) {
        Wxuser wxuser = getById(userId);

        wxuser.setAvatar(photoUrl);

        updateById(wxuser);

        return Result.success();
    }

    @Override
    public VerifyVO getMyEducationalAdministrationPassword() {
        Wxuser user = AccountHolder.getUser();

        return new VerifyVO(user.getStudentNumber(), user.getPasswordJw());
    }

    @Override
    public VerifyVO getMyWisdomSystemPassword() {
        Wxuser user = AccountHolder.getUser();

        return new VerifyVO(user.getStudentNumber(), user.getPasswordPay());
    }

    @Override
    public Result getMybalance() {
        Wxuser user = AccountHolder.getUser();

        return Result.success(user.getBalance());
    }

    @Override
    public Result getStudentCertificationInfo() {

        Wxuser user = AccountHolder.getUser();
        List<StudentAudits> studentAuditsList = studentAuditsMapper.selectList(new QueryWrapper<StudentAudits>().eq("user_id", user.getId()));

        List<StudentAuditsVO> list = studentAuditsList
                .stream()
                .map(studentAudits -> new StudentAuditsVO(studentAudits.getState(),
                        studentAudits.getRemarks(),
                studentAudits.getAuditImageUrl())).toList();

        return Result.success(list);
    }

    @Override
    public Result addStudentCertificationInfo(String studentIdUrl) {

        Wxuser user = AccountHolder.getUser();

        StudentAudits studentAudits = new StudentAudits();
        studentAudits.setAuditId(RandomUtil.generateRandomString(12));
        studentAudits.setStudentid(user.getStudentNumber());
        studentAudits.setUserId(user.getId());
        studentAudits.setAuditImageUrl(studentIdUrl);
        studentAudits.setState(0);

        studentAuditsMapper.insert(studentAudits);
        return Result.success("已经成功申请,审核中!");
    }

    @Override
    public Result processStudentCertification(ProcessStudentCertification processStudentCertification) {

        Integer processCode = processStudentCertification.getProcessCode();

        if (processCode.equals(1)){
            StudentAudits studentAudits = studentAuditsMapper.selectById(processStudentCertification.getAuditId());

            studentAudits.setState(1);
            String userId = studentAudits.getUserId();

            Wxuser wxuser = getById(userId);
            wxuser.setState(6);
            updateById(wxuser);

            studentAuditsMapper.updateById(studentAudits);

          return  Result.success();

        }else {
            StudentAudits studentAudits = studentAuditsMapper.selectById(processStudentCertification.getAuditId());

            studentAudits.setState(2);
            studentAudits.setRemarks(processStudentCertification.getFailureReason());
            studentAuditsMapper.updateById(studentAudits);

            return Result.success();
        }
    }
}




