package com.example.service;

import com.example.common.Result;
import com.example.model.dto.AddCommentRequest;
import com.example.model.dto.PageRequest;
import com.example.model.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;

/**
* @author L
* @description 针对表【comment】的数据库操作Service
* @createDate 2023-09-01 11:52:18
*/
public interface CommentService extends IService<Comment> {
    /**
     * 添加评论
     * @param addCommentRequest 创建评论的请求体
     * @return
     */
    Result addComment(@RequestBody AddCommentRequest addCommentRequest);

    /**
     * 获取评论
     * @param forumId 帖子的id
     * @param pageRequest 分页情况
     * @return
     */
    Result getComments(@NotNull String forumId, @RequestBody PageRequest pageRequest);

    /**
     * 删除评论
     * @param forumId
     * @return
     */
    Result deleteComment(@NotNull String forumId);
}
