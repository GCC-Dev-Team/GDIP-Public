package com.example.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {

    private String commentId;

    /**
     *帖子id
     */
    private String postId;

    /**
     *评论者id
     */
    private String commenterId;

    /**
     *评论内容
     */
    private String commentContent;

    /**
     *点赞数量
     */
    private Integer likes;

    /**
     *踩的情况
     */
    private Integer dislikes;

    /**
     *创建时间
     */
    private Date createdAt;

    /**
     * 用户头像
     */
    private String userAvatar;
    /**
     * 用户名
     */
    private String userName;
}
