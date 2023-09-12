package com.example.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName task
 */
@TableName(value ="task")
@Data
public class Task implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private String id;

    /**
     * 
     */
    @TableField(value = "activity_title")
    private String activityTitle;

    /**
     * 
     */
    @TableField(value = "activity_description")
    private String activityDescription;

    /**
     * 
     */
    @TableField(value = "image_url")
    private String imageUrl;

    /**
     * 
     */
    @TableField(value = "number_of_participants")
    private Integer numberOfParticipants;

    /**
     * 
     */
    @TableField(value = "location")
    private String location;

    /**
     * 
     */
    @TableField(value = "start_time")
    private Date startTime;

    /**
     * 
     */
    @TableField(value = "end_time")
    private Date endTime;

    /**
     * 请求体没有
     */
    @TableField(value = "sign_out_code")
    private String signOutCode;

    /**
     *（0开始接单（已经支付了），1是结束（钱已经付款给接单者），3是未支付，算是暂时保存了（是已经创建了订单了）5:是没有调用微信订单）
     */
    @TableField(value = "status")
    private Integer status;

    @TableField(value = "price")
    private Double price;

    /**
     * 请求体没有
     */
    @TableField(value = "initiator")
    private String initiator;

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

    @TableField(value = "people")
    private int people;

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
        Task other = (Task) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getActivityTitle() == null ? other.getActivityTitle() == null : this.getActivityTitle().equals(other.getActivityTitle()))
            && (this.getActivityDescription() == null ? other.getActivityDescription() == null : this.getActivityDescription().equals(other.getActivityDescription()))
            && (this.getImageUrl() == null ? other.getImageUrl() == null : this.getImageUrl().equals(other.getImageUrl()))
            && (this.getNumberOfParticipants() == null ? other.getNumberOfParticipants() == null : this.getNumberOfParticipants().equals(other.getNumberOfParticipants()))
            && (this.getLocation() == null ? other.getLocation() == null : this.getLocation().equals(other.getLocation()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getSignOutCode() == null ? other.getSignOutCode() == null : this.getSignOutCode().equals(other.getSignOutCode()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getInitiator() == null ? other.getInitiator() == null : this.getInitiator().equals(other.getInitiator()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getUpdatedTime() == null ? other.getUpdatedTime() == null : this.getUpdatedTime().equals(other.getUpdatedTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getActivityTitle() == null) ? 0 : getActivityTitle().hashCode());
        result = prime * result + ((getActivityDescription() == null) ? 0 : getActivityDescription().hashCode());
        result = prime * result + ((getImageUrl() == null) ? 0 : getImageUrl().hashCode());
        result = prime * result + ((getNumberOfParticipants() == null) ? 0 : getNumberOfParticipants().hashCode());
        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getSignOutCode() == null) ? 0 : getSignOutCode().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getInitiator() == null) ? 0 : getInitiator().hashCode());
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
        sb.append(", id=").append(id);
        sb.append(", activityTitle=").append(activityTitle);
        sb.append(", activityDescription=").append(activityDescription);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", numberOfParticipants=").append(numberOfParticipants);
        sb.append(", location=").append(location);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", signOutCode=").append(signOutCode);
        sb.append(", status=").append(status);
        sb.append(", initiator=").append(initiator);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}