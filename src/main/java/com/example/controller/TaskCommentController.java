package com.example.controller;

import com.example.common.Result;
import com.example.model.dto.GetTaskIdRequest;
import com.example.model.dto.PageRequest;
import com.example.model.dto.ParticipateTaskRequest;
import com.example.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 任务（跑腿）通用接口
 */
@RestController
@RequestMapping("/task")
public class TaskCommentController {

    @Resource
    TaskService taskService;
    /**
     * 获取任务的详细信息
     * @param getTaskIdRequest
     * @return
     */
    @PostMapping
    public Result getTaskById(@RequestBody GetTaskIdRequest getTaskIdRequest){

       return taskService.getTaskById(getTaskIdRequest);
    }
    /**
     * 查看所有的活动（活动大厅）
     */
    @PostMapping("/allTask")
    public Result getAllTask(@NotNull @RequestBody PageRequest pageRequest){

        return taskService.getAllTask(pageRequest);
    }
}
