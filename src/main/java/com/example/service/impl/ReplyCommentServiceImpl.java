package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.model.entity.ReplyComment;
import com.example.service.ReplyCommentService;
import com.example.mapper.ReplyCommentMapper;
import org.springframework.stereotype.Service;

/**
* @author L
* @description 针对表【reply_comment】的数据库操作Service实现
* @createDate 2023-09-01 11:52:24
*/
@Service
public class ReplyCommentServiceImpl extends ServiceImpl<ReplyCommentMapper, ReplyComment>
    implements ReplyCommentService{

}




