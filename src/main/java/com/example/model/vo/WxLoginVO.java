package com.example.model.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxLoginVO {
    private Integer role;

    /**
     * 用户名
     */

    private String userName;

    /**
     * 状态，正常是0，封号是1
     */

    private Integer state;

    /**
     * 用户的头像
     */

    private String avatar;

    /**
     * 手机号
     */

    private String phoneNumber;


    /**
     * 学生id
     */

    private String studentNumber;

    /**
     * token
     */
    private String token;


}
