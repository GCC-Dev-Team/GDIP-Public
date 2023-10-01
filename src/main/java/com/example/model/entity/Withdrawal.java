package com.example.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName withdrawal
 */
@TableName(value ="withdrawal")
@Data
public class Withdrawal implements Serializable {
    /**
     * 
     */
    @TableId(value = "withdrawal_id")
    private String withdrawalId;

    /**
     * 
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 0 for pending (deducted balance), 1 for success, 2 for canceled (refund balance)
     * 0是申请提现（已经从余额扣除了），1是成功提现（已经打款呢），2是申请后，没有成功打款，但是又取消申请呢
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 
     */
    @TableField(value = "withdrawal_amount")
    private Double withdrawalAmount;

    /**
     * 卡密
     */
    @TableField(value = "withdrawal_password")
    private String withdrawalPassword;

    /**
     * 
     */
    @TableField(value = "created_at")
    private Date createdAt;

    /**
     * 
     */
    @TableField(value = "updated_at")
    private Date updatedAt;

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
        Withdrawal other = (Withdrawal) that;
        return (this.getWithdrawalId() == null ? other.getWithdrawalId() == null : this.getWithdrawalId().equals(other.getWithdrawalId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getWithdrawalAmount() == null ? other.getWithdrawalAmount() == null : this.getWithdrawalAmount().equals(other.getWithdrawalAmount()))
            && (this.getWithdrawalPassword() == null ? other.getWithdrawalPassword() == null : this.getWithdrawalPassword().equals(other.getWithdrawalPassword()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWithdrawalId() == null) ? 0 : getWithdrawalId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getWithdrawalAmount() == null) ? 0 : getWithdrawalAmount().hashCode());
        result = prime * result + ((getWithdrawalPassword() == null) ? 0 : getWithdrawalPassword().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", withdrawalId=").append(withdrawalId);
        sb.append(", userId=").append(userId);
        sb.append(", status=").append(status);
        sb.append(", withdrawalAmount=").append(withdrawalAmount);
        sb.append(", withdrawalPassword=").append(withdrawalPassword);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}