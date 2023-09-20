package com.example.controller;

import com.example.common.Result;
import com.example.model.dto.PageRequest;
import com.example.model.dto.ParticipateTaskRequest;
import com.example.model.dto.TaskSignOutRequest;
import com.example.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 任务跑腿接单接口
 */
@RestController
@RequestMapping("/taskReceive")
public class TaskReceiveController {

    //状态（0开始接单（已经支付了），
    // 1是结束（钱已经付款给接单者）
    // 2是已经被接单了
    // 3是未支付，算是暂时保存了（是已经创建了订单了）
    // 5:是没有调用微信订单，
    // 6是申请退款的，也就是退款成功的了（没有被接单的时候，也就是0的时候），
    // 7取消订单（已经被接单了，是接单的取消））取消订单完成后，状态是0

    //8`接单者确定送达，数字1是发布者按下确认送达
    @Resource
    TaskService taskService;
    /**
     * 查看我的接单（接单视角）（小图）
     */
    @PostMapping("/myOrder")
    public Result myOrder(@NotNull@RequestBody PageRequest pageRequest){
        return taskService.myOrder(pageRequest);
    }

    /**
     *  取消接单（接单视角），一天限制3次（如果超过了，会扣除信用分）（信用程度）取消后订单状态时0
     */
    @PostMapping("/cancelOrder")
    public Result cancelOrder(@NotNull String taskId){

        return taskService.cancelOrder(taskId);
    }

    /**
     * 接单的确认送达//状态从2变8
     */
    @PutMapping("/delivery")
    public Result deliveryReceive(@NotNull String taskId){

        return taskService.deliveryReceive(taskId);
    }

    /**
     * 报名参加任务，订单状态变成2
     */
    @PostMapping("/participateTask")
    public Result participateTask(@NotNull ParticipateTaskRequest participateTaskRequest){

        return taskService.participateTask(participateTaskRequest);
    }
}
