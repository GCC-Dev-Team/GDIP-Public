package com.example.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName product
 */
@TableName(value ="product")
@Data
public class Product implements Serializable {
    /**
     * 
     */
    @TableId(value = "product_id")
    private String productId;

    /**
     * 
     */
    @TableField(value = "product_title")
    private String productTitle;

    /**
     * 
     */
    @TableField(value = "product_price")
    private Double productPrice;

    /**
     * 
     */
    @TableField(value = "product_unit")
    private String productUnit;

    /**
     * 
     */
    @TableField(value = "product_description")
    private String productDescription;

    /**
     * 
     */
    @TableField(value = "product_image")
    private String productImage;

    /**
     * 
     */
    @TableField(value = "front_image")
    private String frontImage;

    /**
     * 
     */
    @TableField(value = "product_status")
    private Integer productStatus;

    /**
     * 
     */
    @TableField(value = "product_address")
    private String productAddress;

    /**
     * 
     */
    @TableField(value = "publisher_id")
    private String publisherId;

    /**
     * 
     */
    @TableField(value = "favorites_count")
    private Integer favoritesCount;

    /**
     * 
     */
    @TableField(value = "views_count")
    private Integer viewsCount;

    /**
     * 
     */
    @TableField(value = "created_time")
    private Date createdTime;

    /**
     * 
     */
    @TableField(value = "updated_time")
    private Date updatedTime;

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
        Product other = (Product) that;
        return (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getProductTitle() == null ? other.getProductTitle() == null : this.getProductTitle().equals(other.getProductTitle()))
            && (this.getProductPrice() == null ? other.getProductPrice() == null : this.getProductPrice().equals(other.getProductPrice()))
            && (this.getProductUnit() == null ? other.getProductUnit() == null : this.getProductUnit().equals(other.getProductUnit()))
            && (this.getProductDescription() == null ? other.getProductDescription() == null : this.getProductDescription().equals(other.getProductDescription()))
            && (this.getProductImage() == null ? other.getProductImage() == null : this.getProductImage().equals(other.getProductImage()))
            && (this.getFrontImage() == null ? other.getFrontImage() == null : this.getFrontImage().equals(other.getFrontImage()))
            && (this.getProductStatus() == null ? other.getProductStatus() == null : this.getProductStatus().equals(other.getProductStatus()))
            && (this.getProductAddress() == null ? other.getProductAddress() == null : this.getProductAddress().equals(other.getProductAddress()))
            && (this.getPublisherId() == null ? other.getPublisherId() == null : this.getPublisherId().equals(other.getPublisherId()))
            && (this.getFavoritesCount() == null ? other.getFavoritesCount() == null : this.getFavoritesCount().equals(other.getFavoritesCount()))
            && (this.getViewsCount() == null ? other.getViewsCount() == null : this.getViewsCount().equals(other.getViewsCount()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getUpdatedTime() == null ? other.getUpdatedTime() == null : this.getUpdatedTime().equals(other.getUpdatedTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductTitle() == null) ? 0 : getProductTitle().hashCode());
        result = prime * result + ((getProductPrice() == null) ? 0 : getProductPrice().hashCode());
        result = prime * result + ((getProductUnit() == null) ? 0 : getProductUnit().hashCode());
        result = prime * result + ((getProductDescription() == null) ? 0 : getProductDescription().hashCode());
        result = prime * result + ((getProductImage() == null) ? 0 : getProductImage().hashCode());
        result = prime * result + ((getFrontImage() == null) ? 0 : getFrontImage().hashCode());
        result = prime * result + ((getProductStatus() == null) ? 0 : getProductStatus().hashCode());
        result = prime * result + ((getProductAddress() == null) ? 0 : getProductAddress().hashCode());
        result = prime * result + ((getPublisherId() == null) ? 0 : getPublisherId().hashCode());
        result = prime * result + ((getFavoritesCount() == null) ? 0 : getFavoritesCount().hashCode());
        result = prime * result + ((getViewsCount() == null) ? 0 : getViewsCount().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getUpdatedTime() == null) ? 0 : getUpdatedTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", productId=").append(productId);
        sb.append(", productTitle=").append(productTitle);
        sb.append(", productPrice=").append(productPrice);
        sb.append(", productUnit=").append(productUnit);
        sb.append(", productDescription=").append(productDescription);
        sb.append(", productImage=").append(productImage);
        sb.append(", frontImage=").append(frontImage);
        sb.append(", productStatus=").append(productStatus);
        sb.append(", productAddress=").append(productAddress);
        sb.append(", publisherId=").append(publisherId);
        sb.append(", favoritesCount=").append(favoritesCount);
        sb.append(", viewsCount=").append(viewsCount);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}