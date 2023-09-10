package com.example.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderVO {

    private String timeStamp;
    private String nonceStr;

    private String packageValue;
    private String signType;
    private String paySign;
    /**
     * 下单的微信订单号
     */
    private String outTradeNo;
}
