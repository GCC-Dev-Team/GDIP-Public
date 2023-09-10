package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRefundDTO {
    /**
     * 原支付订单号
     */
    private String outTradeNo;
    /**
     * 订单总价
     */
    private Double totalFee;

    /**
     * 退款价格
     */
    private Double refundFee;
}
