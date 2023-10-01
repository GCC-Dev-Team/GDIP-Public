package com.example.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName balance_records
 */
@TableName(value ="balance_records")
@Data
public class BalanceRecords implements Serializable {
    /**
     * 
     */
    @TableId(value = "balance_id")
    private String balanceId;

    /**
     * 
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 当前余额
     */
    @TableField(value = "current_balance")
    private Double currentBalance;

    /**
     * 0表示增加（收入），1表示减少（支出）
     */
    @TableField(value = "balance_type")
    private Integer balanceType;

    /**
     * 
     */
    @TableField(value = "balance_change")
    private Double balanceChange;

    /**
     * 提现订单号
     */
    @TableField(value = "withdrawal_order_id")
    private String withdrawalOrderId;

    /**
     * 支付订单号
     */
    @TableField(value = "payment_order_id")
    private String paymentOrderId;

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
        BalanceRecords other = (BalanceRecords) that;
        return (this.getBalanceId() == null ? other.getBalanceId() == null : this.getBalanceId().equals(other.getBalanceId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCurrentBalance() == null ? other.getCurrentBalance() == null : this.getCurrentBalance().equals(other.getCurrentBalance()))
            && (this.getBalanceType() == null ? other.getBalanceType() == null : this.getBalanceType().equals(other.getBalanceType()))
            && (this.getBalanceChange() == null ? other.getBalanceChange() == null : this.getBalanceChange().equals(other.getBalanceChange()))
            && (this.getWithdrawalOrderId() == null ? other.getWithdrawalOrderId() == null : this.getWithdrawalOrderId().equals(other.getWithdrawalOrderId()))
            && (this.getPaymentOrderId() == null ? other.getPaymentOrderId() == null : this.getPaymentOrderId().equals(other.getPaymentOrderId()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBalanceId() == null) ? 0 : getBalanceId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCurrentBalance() == null) ? 0 : getCurrentBalance().hashCode());
        result = prime * result + ((getBalanceType() == null) ? 0 : getBalanceType().hashCode());
        result = prime * result + ((getBalanceChange() == null) ? 0 : getBalanceChange().hashCode());
        result = prime * result + ((getWithdrawalOrderId() == null) ? 0 : getWithdrawalOrderId().hashCode());
        result = prime * result + ((getPaymentOrderId() == null) ? 0 : getPaymentOrderId().hashCode());
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
        sb.append(", balanceId=").append(balanceId);
        sb.append(", userId=").append(userId);
        sb.append(", currentBalance=").append(currentBalance);
        sb.append(", balanceType=").append(balanceType);
        sb.append(", balanceChange=").append(balanceChange);
        sb.append(", withdrawalOrderId=").append(withdrawalOrderId);
        sb.append(", paymentOrderId=").append(paymentOrderId);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}