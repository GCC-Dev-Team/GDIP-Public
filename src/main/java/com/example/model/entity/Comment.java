package com.example.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    /**
     * 
     */
    @TableId(value = "comment_id")
    private String commentId;

    /**
     * 
     */
    @TableField(value = "post_id")
    private String postId;

    /**
     * 
     */
    @TableField(value = "commenter_id")
    private String commenterId;

    /**
     * 
     */
    @TableField(value = "comment_content")
    private String commentContent;

    /**
     * 
     */
    @TableField(value = "likes")
    private Integer likes;

    /**
     * 
     */
    @TableField(value = "dislikes")
    private Integer dislikes;

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
     * 子评论条数
     */
    @TableField(value = "sub_comment_count")
    private Integer subCommentCount;

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
        Comment other = (Comment) that;
        return (this.getCommentId() == null ? other.getCommentId() == null : this.getCommentId().equals(other.getCommentId()))
            && (this.getPostId() == null ? other.getPostId() == null : this.getPostId().equals(other.getPostId()))
            && (this.getCommenterId() == null ? other.getCommenterId() == null : this.getCommenterId().equals(other.getCommenterId()))
            && (this.getCommentContent() == null ? other.getCommentContent() == null : this.getCommentContent().equals(other.getCommentContent()))
            && (this.getLikes() == null ? other.getLikes() == null : this.getLikes().equals(other.getLikes()))
            && (this.getDislikes() == null ? other.getDislikes() == null : this.getDislikes().equals(other.getDislikes()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCommentId() == null) ? 0 : getCommentId().hashCode());
        result = prime * result + ((getPostId() == null) ? 0 : getPostId().hashCode());
        result = prime * result + ((getCommenterId() == null) ? 0 : getCommenterId().hashCode());
        result = prime * result + ((getCommentContent() == null) ? 0 : getCommentContent().hashCode());
        result = prime * result + ((getLikes() == null) ? 0 : getLikes().hashCode());
        result = prime * result + ((getDislikes() == null) ? 0 : getDislikes().hashCode());
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
        sb.append(", commentId=").append(commentId);
        sb.append(", postId=").append(postId);
        sb.append(", commenterId=").append(commenterId);
        sb.append(", commentContent=").append(commentContent);
        sb.append(", likes=").append(likes);
        sb.append(", dislikes=").append(dislikes);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}