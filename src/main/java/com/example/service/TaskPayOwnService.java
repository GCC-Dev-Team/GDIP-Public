package com.example.service;

import com.example.model.vo.CreateOrderVO;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * 第三层封装，Task支付接口（主要负责task表）
 */

public interface TaskPayOwnService {

    /**
     * 创建订单(未支付，10钟内请支付)
     * @param taskId 任务订单id
     */
    CreateOrderVO createTaskOrder(@NotNull String taskId);


    /**
     * 成功支付回调后改变任务状态的（任务用的）
     * @param outTradeNo 微信订单号
     * @return Bool
     */
    Boolean successTaskNotify(@NotNull String outTradeNo);

    /**
     * 成功退款后回调用的
     */
    Boolean refundTaskNotify(@NotNull String outRefundNo);

    /**
     * 重新支付
     */
    CreateOrderVO rePay(@NotNull String taskId);
}
