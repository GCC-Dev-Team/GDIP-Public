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

    /**
     * 校园通平台1
     */
    private String passwordJw;

    /**
     * 就的校园通平台1
     */

    private String oldPasswordJw;

    /**
     *校园通3.0平台1
     */


    private String passwordNew;

    /**
     * 旧的校园通密码
     */
    private String oldPasswordNew;

}
