package com.example.service;

import com.example.common.Result;
import com.example.model.dto.GetTaskIdRequest;
import com.example.model.dto.TaskCreateRequest;
import com.example.model.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

/**
* @author L
* @description 针对表【task】的数据库操作Service
* @createDate 2023-07-31 10:56:35
*/
public interface TaskService extends IService<Task> {

    Result createTask(@RequestBody TaskCreateRequest taskCreateRequest);

    Result getTaskById(@RequestBody GetTaskIdRequest getTaskIdRequest);

    Result getTaskSmall();
}
