package com.example.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName student_audits
 */
@TableName(value ="student_audits")
@Data
public class StudentAudits implements Serializable {
    /**
     * 
     */
    @TableId(value = "audit_id")
    private String auditId;

    /**
     * 
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 
     */
    @TableField(value = "state")
    private Integer state;

    /**
     * 
     */
    @TableField(value = "remarks")
    private String remarks;

    /**
     * 
     */
    @TableField(value = "audit_image_url")
    private String auditImageUrl;

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

    /**
     * 
     */
    @TableField(value = "studentId")
    private String studentid;

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
        StudentAudits other = (StudentAudits) that;
        return (this.getAuditId() == null ? other.getAuditId() == null : this.getAuditId().equals(other.getAuditId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getAuditImageUrl() == null ? other.getAuditImageUrl() == null : this.getAuditImageUrl().equals(other.getAuditImageUrl()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getStudentid() == null ? other.getStudentid() == null : this.getStudentid().equals(other.getStudentid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAuditId() == null) ? 0 : getAuditId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getAuditImageUrl() == null) ? 0 : getAuditImageUrl().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getStudentid() == null) ? 0 : getStudentid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", auditId=").append(auditId);
        sb.append(", userId=").append(userId);
        sb.append(", state=").append(state);
        sb.append(", remarks=").append(remarks);
        sb.append(", auditImageUrl=").append(auditImageUrl);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", studentid=").append(studentid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}