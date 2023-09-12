package com.example.controller;

import com.example.common.Result;
import com.example.model.dto.PageRequest;
import com.example.model.dto.TaskCreateRequest;
import com.example.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/taskPublisher")
public class TaskPublisherController {
    @Resource
    TaskService taskService;
    /**
     * 申请退款（发布者角度）会自动删除订单
     */
    //订单状态变成6（不会展示）（当task的状态是0的时候才可以退款，因为接单后，需要接单的取消才可以）
    @PutMapping
    public Result refund(@NotNull String taskId){

        return taskService.refund(taskId);
    }


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
     * 查看我发布的任务（小图）
     */
    @PostMapping("/myTask")
    public Result getTaskSmall(@NotNull @RequestBody PageRequest pageRequest){

        return taskService.getTaskSmall(pageRequest);

    }

    /**
     * 删除活动，根据taskid
     * @param taskId
     * @return
     */
    @DeleteMapping
    public  Result deleteTask(@NotNull String taskId){

        return taskService.deleteTask(taskId);
    }

}
