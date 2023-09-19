package com.example.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyVO {
    /**
     * 学号
     */
    private String studentNumber;

    /**
     * 密码
     */
    private String password;
}
