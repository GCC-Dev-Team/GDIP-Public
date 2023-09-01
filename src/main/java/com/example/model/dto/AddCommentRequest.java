package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentRequest {
    /**
     *帖子的id
     */
    private String postId;
    /**
     *评论内容
     */
    private String commentContent;

}
