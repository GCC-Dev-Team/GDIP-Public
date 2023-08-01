package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSchoolPassword3Request {
    /**
     * 学生id号
     */
    private String studentId;
    /**
     *校园通3.0平台1
     */


    private String passwordNew;

    /**
     * 旧的校园通密码
     */
    private String oldPasswordNew;
}
