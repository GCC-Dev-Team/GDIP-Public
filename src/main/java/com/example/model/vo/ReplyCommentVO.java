package com.example.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyCommentVO {
    /**
     * 回复评论id
     */
    private String replyId;

    /**
     *用户id
     */
    private String userId;

    /**
     *根评论id
     */
    private String commentId;

    /**
     *评论内容（子节点）
     */
    private String content;

    /**
     *喜欢数
     */
    private Integer likes;

    /**
     *踩数
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
