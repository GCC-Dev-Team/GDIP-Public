package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {
    /**
     * 微信支付产品id（商品则是productId），任务则是taskId
     */
    private String productId;

    private String body;

    private Double totalPrice;

}
