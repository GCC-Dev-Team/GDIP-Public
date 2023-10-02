package com.example.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceReceiveAndPayDTO {
    /**
     * 0表示增加（收入），1表示减少（支出）,也就是以后开发可以余额支付，也是可以记录下来的
     */
    private Integer balanceType;
    /**
     *
     */
    private Double balanceChange;
    /**
     * 支付订单号
     */
    private String paymentOrderId;
}
