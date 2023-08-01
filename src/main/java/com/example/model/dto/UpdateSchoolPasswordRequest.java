package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSchoolPasswordRequest {
    /**
     * 学生id号
     */

    private String studentId;

    /**
     * 校园通平台1
     */
    private String passwordJw;

    /**
     * 就的校园通平台1
     */

    private String oldPasswordJw;

}
