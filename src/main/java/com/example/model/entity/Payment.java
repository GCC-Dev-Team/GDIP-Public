package com.example.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName payment
 */
@TableName(value ="payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    /**
     * 微信订单编号
     */
    @TableId(value = "out_trade_no")
    private String outTradeNo;

    /**
     * 商品的id(任务支付下是任务id)
     */
    @TableField(value = "product_id")
    private String productId;

    /**
     * 状态码
     */
    @TableField(value = "status_code")
    private String statusCode;

    /**
     * 状态数字
     */
    @TableField(value = "status_number")
    private Integer statusNumber;

    /**
     * 出售者
     */
    @TableField(value = "recipient")
    private String recipient;

    /**
     * 购买者（支付者）
     */
    @TableField(value = "payer")
    private String payer;

    /**
     * 微信支付必备的
     */
    @TableField(value = "package_value")
    private String packageValue;

    /**
     * 
     */
    @TableField(value = "pay_sign")
    private String paySign;

    /**
     * 
     */
    @TableField(value = "time_stamp")
    private String timeStamp;

    /**
     * 
     */
    @TableField(value = "nonce_str")
    private String nonceStr;

    /**
     * 
     */
    @TableField(value = "sign_type")
    private String signType;

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
        Payment other = (Payment) that;
        return (this.getOutTradeNo() == null ? other.getOutTradeNo() == null : this.getOutTradeNo().equals(other.getOutTradeNo()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getStatusCode() == null ? other.getStatusCode() == null : this.getStatusCode().equals(other.getStatusCode()))
            && (this.getStatusNumber() == null ? other.getStatusNumber() == null : this.getStatusNumber().equals(other.getStatusNumber()))
            && (this.getRecipient() == null ? other.getRecipient() == null : this.getRecipient().equals(other.getRecipient()))
            && (this.getPayer() == null ? other.getPayer() == null : this.getPayer().equals(other.getPayer()))
            && (this.getPackageValue() == null ? other.getPackageValue() == null : this.getPackageValue().equals(other.getPackageValue()))
            && (this.getPaySign() == null ? other.getPaySign() == null : this.getPaySign().equals(other.getPaySign()))
            && (this.getTimeStamp() == null ? other.getTimeStamp() == null : this.getTimeStamp().equals(other.getTimeStamp()))
            && (this.getNonceStr() == null ? other.getNonceStr() == null : this.getNonceStr().equals(other.getNonceStr()))
            && (this.getSignType() == null ? other.getSignType() == null : this.getSignType().equals(other.getSignType()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOutTradeNo() == null) ? 0 : getOutTradeNo().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getStatusCode() == null) ? 0 : getStatusCode().hashCode());
        result = prime * result + ((getStatusNumber() == null) ? 0 : getStatusNumber().hashCode());
        result = prime * result + ((getRecipient() == null) ? 0 : getRecipient().hashCode());
        result = prime * result + ((getPayer() == null) ? 0 : getPayer().hashCode());
        result = prime * result + ((getPackageValue() == null) ? 0 : getPackageValue().hashCode());
        result = prime * result + ((getPaySign() == null) ? 0 : getPaySign().hashCode());
        result = prime * result + ((getTimeStamp() == null) ? 0 : getTimeStamp().hashCode());
        result = prime * result + ((getNonceStr() == null) ? 0 : getNonceStr().hashCode());
        result = prime * result + ((getSignType() == null) ? 0 : getSignType().hashCode());
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
        sb.append(", outTradeNo=").append(outTradeNo);
        sb.append(", productCode=").append(productId);
        sb.append(", statusCode=").append(statusCode);
        sb.append(", statusNumber=").append(statusNumber);
        sb.append(", recipient=").append(recipient);
        sb.append(", payer=").append(payer);
        sb.append(", packageValue=").append(packageValue);
        sb.append(", paySign=").append(paySign);
        sb.append(", timeStamp=").append(timeStamp);
        sb.append(", nonceStr=").append(nonceStr);
        sb.append(", signType=").append(signType);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}