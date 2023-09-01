package com.example.service;

import com.example.common.Result;
import com.example.model.dto.AddReplyCommentRequest;
import com.example.model.dto.PageRequest;
import com.example.model.entity.ReplyComment;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;

/**
* @author L
* @description 针对表【reply_comment】的数据库操作Service
* @createDate 2023-09-01 11:52:24
*/
public interface ReplyCommentService extends IService<ReplyComment> {
    Result addReplyComment(@RequestBody AddReplyCommentRequest addReplyCommentRequest);

    Result getReplyComment(@NotNull String commentId, @RequestBody PageRequest pageRequest);

    Result deleteReplyComment(@NotNull String replyId);

}
