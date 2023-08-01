package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxLoginRequest {
    /**
     * 微信登录的授权码
     */
    private String code;

}
