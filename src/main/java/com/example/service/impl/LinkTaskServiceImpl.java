package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.model.entity.LinkTask;
import com.example.service.LinkTaskService;
import com.example.mapper.LinkTaskMapper;
import org.springframework.stereotype.Service;

/**
* @author L
* @description 针对表【link_task】的数据库操作Service实现
* @createDate 2023-08-01 10:21:07
*/
@Service
public class LinkTaskServiceImpl extends ServiceImpl<LinkTaskMapper, LinkTask>
    implements LinkTaskService{

}




