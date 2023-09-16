package com.example.controller;

import com.example.common.Result;

import com.example.model.vo.CreateOrderVO;
import com.example.service.PayOwn;
import com.example.service.TaskPayOwnService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 任务跑腿支付接口
 */
@RestController
@RequestMapping("/taskPay")
public class TaskPayController {
    @Resource
    TaskPayOwnService taskPayOwnService;
    @Resource
    PayOwn payOwn;

    /**
     * 创建任务后，调起微信支付 状态变成3（没支付）或者变成0（成功支付）
     * @param taskId
     * @return
     */
    @PostMapping
    public Result addTrade(@NotNull String taskId){

        return Result.success(taskPayOwnService.createTaskOrder(taskId));
    }


    /**
     * 根据产品号查询订单情况（查询是否付款）
     *
     * @param
     * @return
     */
    @GetMapping("/trade")
    public Result queryTrade(@NotNull String taskId) {

        return Result.success(payOwn.queryOrder(taskId));
    }

    /**
     * 重新付款
     */
    @PostMapping("/rePay")
    CreateOrderVO rePay(@NotNull String taskId){
        return taskPayOwnService.rePay(taskId);
    }
}
