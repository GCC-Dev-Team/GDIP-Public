package com.example.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName wxuser
 */
@TableName(value ="wxuser")
@Data
public class Wxuser implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private String id;

    /**
     * 微信的openid
     */
    @TableField(value = "openid")
    private String openid;

    /**
     * 角色，0是用户，1是管理员
     */
    @TableField(value = "role")
    private Integer role;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * （默认（未认证）正常（0）、封禁（2）、注销（3）、已认证（1））5是管理员,6是上传学生证并且绑定教务系统的
     */
    @TableField(value = "state")
    private Integer state;

    /**
     * 用户的头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 手机号
     */
    @TableField(value = "phone_number")
    private String phoneNumber;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 学生id
     */
    @TableField(value = "student_number")
    private String studentNumber;

    /**
     * 
     */
    @TableField(value = "password_jw")
    private String passwordJw;

    /**
     * 
     */
    @TableField(value = "password_pay")
    private String passwordPay;

    /**
     * 
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 
     */
    @TableField(value = "update_time")
    private Date updateTime;
    /**
     * 当前余额
     */
    @TableField(value = "balance")
    private Double balance;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Wxuser other = (Wxuser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()))
            && (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
            && (this.getPhoneNumber() == null ? other.getPhoneNumber() == null : this.getPhoneNumber().equals(other.getPhoneNumber()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getStudentNumber() == null ? other.getStudentNumber() == null : this.getStudentNumber().equals(other.getStudentNumber()))
            && (this.getPasswordJw() == null ? other.getPasswordJw() == null : this.getPasswordJw().equals(other.getPasswordJw()))
            && (this.getPasswordPay() == null ? other.getPasswordPay() == null : this.getPasswordPay().equals(other.getPasswordPay()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getPhoneNumber() == null) ? 0 : getPhoneNumber().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getStudentNumber() == null) ? 0 : getStudentNumber().hashCode());
        result = prime * result + ((getPasswordJw() == null) ? 0 : getPasswordJw().hashCode());
        result = prime * result + ((getPasswordPay() == null) ? 0 : getPasswordPay().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", openid=").append(openid);
        sb.append(", role=").append(role);
        sb.append(", userName=").append(userName);
        sb.append(", state=").append(state);
        sb.append(", avatar=").append(avatar);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", password=").append(password);
        sb.append(", studentNumber=").append(studentNumber);
        sb.append(", passwordJw=").append(passwordJw);
        sb.append(", passwordPay=").append(passwordPay);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}