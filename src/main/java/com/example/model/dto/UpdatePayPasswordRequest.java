package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 支付密码请求体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePayPasswordRequest {
    private String oldPassword;

    private String newPassword;
}
