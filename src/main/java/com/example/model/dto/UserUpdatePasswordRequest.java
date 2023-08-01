package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdatePasswordRequest {
    /**
     * 个人平台的密码
     */
    private String password;
    /**
     * 旧的个人平台的密码
     */

    private String oldPassword;
}
