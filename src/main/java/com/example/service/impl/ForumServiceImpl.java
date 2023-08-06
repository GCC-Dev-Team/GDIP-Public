package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.model.entity.Forum;
import com.example.service.ForumService;
import com.example.mapper.ForumMapper;
import org.springframework.stereotype.Service;

/**
* @author L
* @description 针对表【forum】的数据库操作Service实现
* @createDate 2023-08-06 12:43:34
*/
@Service
public class ForumServiceImpl extends ServiceImpl<ForumMapper, Forum>
    implements ForumService{

}




