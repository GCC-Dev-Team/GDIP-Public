package com.example.controller;

import com.example.common.Result;
import com.example.model.dto.GetTaskIdRequest;
import com.example.model.dto.TaskCreateRequest;
import com.example.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Resource
    TaskService taskService;

    /**
     * 发布任务
     * @param taskCreateRequest
     * @return
     */
    @PostMapping
    public Result createTask(@RequestBody TaskCreateRequest taskCreateRequest){

        return taskService.createTask(taskCreateRequest);
    }

    /**
     * 获取任务的详细信息
     * @param getTaskIdRequest
     * @return
     */
    @GetMapping
    public Result getTaskById(@RequestBody GetTaskIdRequest getTaskIdRequest){

       return taskService.getTaskById(getTaskIdRequest);
    }
    /**
     * 查看我发布的任务
     */
    @GetMapping("/myTask")
    public Result getTaskSmall(){
        return taskService.getTaskSmall();
    }
}
