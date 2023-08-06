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
 * @TableName forum
 */
@TableName(value ="forum")
@Data
public class Forum implements Serializable {
    /**
     * 帖子id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 帖子标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 帖子小图
     */
    @TableField(value = "surface_image")
    private String surfaceImage;

    /**
     * 帖子发布者
     */
    @TableField(value = "publisher")
    private String publisher;

    /**
     * 帖子的文件url
     */
    @TableField(value = "md_file_url")
    private String mdFileUrl;

    /**
     * 帖子的浏览量
     */
    @TableField(value = "views")
    private Integer views;

    /**
     * 帖子的分类
     */
    @TableField(value = "category")
    private String category;

    /**
     * 帖子的创建时间
     */
    @TableField(value = "created_at")
    private Date createdAt;

    /**
     * 帖子的修改时间
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
        Forum other = (Forum) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getSurfaceImage() == null ? other.getSurfaceImage() == null : this.getSurfaceImage().equals(other.getSurfaceImage()))
            && (this.getPublisher() == null ? other.getPublisher() == null : this.getPublisher().equals(other.getPublisher()))
            && (this.getMdFileUrl() == null ? other.getMdFileUrl() == null : this.getMdFileUrl().equals(other.getMdFileUrl()))
            && (this.getViews() == null ? other.getViews() == null : this.getViews().equals(other.getViews()))
            && (this.getCategory() == null ? other.getCategory() == null : this.getCategory().equals(other.getCategory()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getSurfaceImage() == null) ? 0 : getSurfaceImage().hashCode());
        result = prime * result + ((getPublisher() == null) ? 0 : getPublisher().hashCode());
        result = prime * result + ((getMdFileUrl() == null) ? 0 : getMdFileUrl().hashCode());
        result = prime * result + ((getViews() == null) ? 0 : getViews().hashCode());
        result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
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
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", surfaceImage=").append(surfaceImage);
        sb.append(", publisher=").append(publisher);
        sb.append(", mdFileUrl=").append(mdFileUrl);
        sb.append(", views=").append(views);
        sb.append(", category=").append(category);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}