package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmBalanceRequest {

    /**
     * 操作人员名称
     */
    private String userName;
    /**
     * 后台操作密码
     */
    private String confirmPassword;

    /**
     * 打款的卡密
     */
    private String withdrawalPassword;

    /**
     * 已打款的提现单号
     */
    private String withdrawalId;
}
