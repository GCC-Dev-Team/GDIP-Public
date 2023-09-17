package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 支付回调地址
 */
@Data
@Component
@ConfigurationProperties(prefix="callback")
public class CallbackAddressProperties {
    /**
     * 支付成功回调地址
     */
    private String successAddress;

    /**
     * 退款成功回调地址
     */
    private String refundAddress;
}
