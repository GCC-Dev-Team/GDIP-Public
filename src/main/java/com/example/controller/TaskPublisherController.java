package com.example.controller;

import com.example.common.Result;
import com.example.model.dto.PageRequest;
import com.example.model.dto.TaskCreateRequest;
import com.example.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 任务跑腿发布者接口
 */
@RestController
@RequestMapping("/taskPublisher")
public class TaskPublisherController {
    //状态（0开始接单（已经支付了），
    // 1是结束（钱已经付款给接单者）
    // 2是已经被接单了
    // 3是未支付，算是暂时保存了（是已经创建了订单了）
    // 5:是没有调用微信订单，
    // 6是申请退款的，也就是退款成功的了（没有被接单的时候，也就是0的时候才能退款），
    // 7取消订单（已经被接单了，是接单的取消））取消订单完成后，状态是0
    @Resource
    TaskService taskService;
    /**
     * 申请退款（发布者角度）会自动删除订单 状态时6
     */
    //订单状态变成6，退款成功后（不会展示在任务大厅）（当task的状态是0的时候才可以退款，因为接单后，需要接单的取消才可以）
    @PutMapping
    public Result refund(@NotNull String taskId){

        return taskService.refund(taskId);
    }


    /**
     * 发布任务 任务状态是 5
     * @param taskCreateRequest
     * @return
     */
    @PostMapping
    public Result createTask(@RequestBody TaskCreateRequest taskCreateRequest){

        return taskService.createTask(taskCreateRequest);
    }

    /**
     * 查看我发布的任务（小图）查看我发布的任务状态 全数字都可以
     */
    @PostMapping("/myTask")
    public Result getTaskSmall(@NotNull @RequestBody PageRequest pageRequest){

        return taskService.getTaskSmall(pageRequest);

    }

    /**
     * 删除活动，根据taskid 只能删除3 5 6
     * @param taskId
     * @return
     */
    @DeleteMapping
    public  Result deleteTask(@NotNull String taskId){

        return taskService.deleteTask(taskId);
    }
    /**
     * 卖家的确认送达，状态从8变1
     */
    @PutMapping("/delivery")
    public Result deliveryPublisher(@NotNull String taskId){
        return taskService.deliveryPublisher(taskId);
    }

}
