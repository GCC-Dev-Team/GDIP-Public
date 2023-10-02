package com.example.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVO {
    /**
     * 用户名
     */

    private String userName;
    /**
     * 用户的头像
     */

    private String avatar;

    /**
     * 状态
     */

    private Integer state;

    /**
     * 手机号
     */

    private String phoneNumber;


    /**
     * 学生id
     */

    private String studentNumber;

    /**
     * 查看是否绑定了教务系统
     */
    private Boolean isBindSystemEdu;

    /**
     * 当前余额
     */
    private Double balance;
}
