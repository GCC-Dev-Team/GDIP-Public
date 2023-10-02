package com.example.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
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
    @TableField(value = "balance_type")
    private Integer balanceType;
    /**
     *
     */
    @TableField(value = "balance_change")
    private Double balanceChange;
    /**
     * 支付订单号
     */
    @TableField(value = "payment_order_id")
    private String paymentOrderId;
}
