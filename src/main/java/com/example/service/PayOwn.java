package com.example.service;

import com.example.model.dto.CreateOrderDTO;
import com.example.model.dto.CreateRefundDTO;
import com.example.model.entity.Payment;
import com.example.model.vo.CreateOrderVO;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import org.springframework.stereotype.Service;


import javax.validation.constraints.NotNull;

/**
 * 自己的支付接口，是对微信支付解耦封装(二次封装)
 */

public interface PayOwn {

    /**
     * 统一下单接口（通用型）
     * @return
     */
    Payment createOrderByOwnPayInterface(@NotNull CreateOrderDTO createOrderDTO);

    /**
     * 根据ProductId(TaskId)查询（通用型）
     */
    String queryOrder(@NotNull String generalId);

    /**
     * 申请退款（通用型）
     * @param createRefundDTO 退款请求体
     * @return
     */
    Boolean refund(@NotNull CreateRefundDTO createRefundDTO);

    /**
     * 通用关闭订单
     * @param outTradeNo
     */
    void closeOrder(@NotNull String outTradeNo);

    /**
     * 成功支付回调更新payment
     * @param outTradeNo
     * @return
     */
    Payment successNotify(@NotNull String outTradeNo);

    /**
     * 申请退款回调（更新refund）
     * @param outRefundNo
     * @return
     */
    Boolean refundNotify(String outRefundNo);

    /**
     * 根据outTradeNo获取支付表的productId，判断任务还是商品
     */
    String getGeneralId(@NotNull String outTradeNo);

}
