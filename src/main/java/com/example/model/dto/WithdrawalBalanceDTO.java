package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawalBalanceDTO {

    /**
     *提现/取消提现金额
     */
    private Double balanceChange;
    /**
     * 提现订单号
     */
    private String withdrawalId;

    /**
     * 提现/取消提现的用户id
     */
    private String userId;
}
