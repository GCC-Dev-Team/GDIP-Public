package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.model.dto.TaskCreateRequest;
import com.example.model.entity.Task;
import com.example.service.TaskService;
import com.example.mapper.TaskMapper;
import org.springframework.stereotype.Service;

/**
* @author L
* @description 针对表【task】的数据库操作Service实现
* @createDate 2023-07-31 10:56:35
*/
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task>
    implements TaskService{
    @Override
    public Result createTask(TaskCreateRequest taskCreateRequest) {
        //Todo
        return null;
    }
}




