package com.example.service;

import javax.validation.constraints.NotNull;

public interface AccountJudgmentService {

    /**
     * 判断教务系统密码和账号是否正确(正确则是认证成功，用户状态也就变成1（成功认证的），0是正常用户没有认证的)
     * @param schoolId
     * @param password
     * @return
     */
    Boolean judgeIsAccount(@NotNull String schoolId,@NotNull String password);

}
