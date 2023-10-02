package com.example.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BalanceDetailVO {
    /**
     * 当前余额（改变后的）
     */
    private Double currentBalance;

    /**
     * 0表示增加（收入），1表示减少（支出）
     */
    private Integer balanceType;

    /**
     * 改变金额
     */
    private Double balanceChange;

}
