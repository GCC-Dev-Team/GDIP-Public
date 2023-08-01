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
 * @TableName link_task
 */
@TableName(value ="link_task")
@Data
public class LinkTask implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private String id;

    /**
     * 
     */
    @TableField(value = "task_id")
    private String taskId;

    /**
     * 
     */
    @TableField(value = "participant_id")
    private String participantId;

    /**
     * 
     */
    @TableField(value = "is_signed_out")
    private Integer isSignedOut;

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
        LinkTask other = (LinkTask) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getParticipantId() == null ? other.getParticipantId() == null : this.getParticipantId().equals(other.getParticipantId()))
            && (this.getIsSignedOut() == null ? other.getIsSignedOut() == null : this.getIsSignedOut().equals(other.getIsSignedOut()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getUpdatedTime() == null ? other.getUpdatedTime() == null : this.getUpdatedTime().equals(other.getUpdatedTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getParticipantId() == null) ? 0 : getParticipantId().hashCode());
        result = prime * result + ((getIsSignedOut() == null) ? 0 : getIsSignedOut().hashCode());
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
        sb.append(", taskId=").append(taskId);
        sb.append(", participantId=").append(participantId);
        sb.append(", isSignedOut=").append(isSignedOut);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}