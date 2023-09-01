package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.model.entity.Comment;
import com.example.service.CommentService;
import com.example.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author L
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2023-09-01 11:52:18
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




