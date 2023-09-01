package com.example.controller;

import com.example.common.Result;
import com.example.model.dto.AddReplyCommentRequest;
import com.example.model.dto.PageRequest;
import com.example.service.ReplyCommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 回复评论（子评论）
 */
@RestController
@RequestMapping("/replyComment")
public class ReplyCommentController {
    @Resource
    ReplyCommentService  replyCommentService;

    /**
     * 评论回复（评论评论）
     * @param addReplyCommentRequest
     * @return
     */
    @PostMapping
    public Result addReplyComment(@RequestBody AddReplyCommentRequest addReplyCommentRequest){

      return   replyCommentService.addReplyComment(addReplyCommentRequest);
    }

    /**
     * 得到某个评论的所有回复
     * @param commentId
     * @param pageRequest
     * @return
     */
    @PostMapping("/getReply")
    public Result getReplyComment(@NotNull String commentId, @RequestBody PageRequest pageRequest){

        return replyCommentService.getReplyComment(commentId,pageRequest);
    }

    /**
     * 删除回复
     * @param replyId
     * @return
     */
    @DeleteMapping
    public Result deleteReplyComment(@NotNull String replyId){

        return replyCommentService.deleteReplyComment(replyId);
    }


}
