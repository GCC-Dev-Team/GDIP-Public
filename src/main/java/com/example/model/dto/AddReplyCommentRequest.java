package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddReplyCommentRequest {
    /**
     * 根评论的id号
     */
    private String commentId;

    /**
     *回复评论的内容
     */
    private String content;
}
