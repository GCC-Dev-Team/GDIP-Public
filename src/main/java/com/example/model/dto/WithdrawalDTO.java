package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawalDTO {
    /**
     * 密码
     */
    private String payPassword;

    /**
     * 提现金额
     */
    private Double balance;
}
