package com.example.service;

import com.example.model.vo.CreateOrderVO;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * 第三层封装，Task支付接口（主要负责task表）
 */
@Service
public interface TaskPayOwnService {

    /**
     * 创建订单(未支付，10钟内请支付)
     * @param taskId
     * @return
     */
    CreateOrderVO createTaskOrder(@NotNull String taskId);


    /**
     * 成功支付回调后改变任务状态的（任务用的）
     * @param outTradeNo
     * @return
     */
    Boolean successTaskNotify(@NotNull String outTradeNo);

    /**
     * 成功退款后回调用的，用来改变task的状态和payment和refund状态
     * @param outRefundNo
     * @return
     */
    Boolean refundTaskNotify(@NotNull String outRefundNo);
}
