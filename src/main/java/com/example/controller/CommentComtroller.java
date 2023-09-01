package com.example.controller;

import com.example.common.Result;
import com.example.model.dto.AddCommentRequest;
import com.example.model.dto.PageRequest;
import com.example.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 根评论
 */
@RestController
@RequestMapping("/comment")
public class CommentComtroller {

    @Resource
    CommentService commentService;


    /**
     * 添加评论主评
     * @param addCommentRequest
     * @return
     */
    @PostMapping
    public Result addComment(@RequestBody AddCommentRequest addCommentRequest){
        return commentService.addComment(addCommentRequest);
    }

    /**
     * 获取某个帖子的所有评论
     * @param forumId
     * @param pageRequest
     * @return
     */
    @PostMapping("/getComment")
    public Result getComments(@NotNull String forumId, @RequestBody PageRequest pageRequest){

        return commentService.getComments(forumId,pageRequest);
    }

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    @DeleteMapping
    public Result deleteComment(@NotNull String commentId){

        return commentService.deleteComment(commentId);
    }





}
