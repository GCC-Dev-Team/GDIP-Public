package com.example.service;

import com.example.model.vo.CreateOrderVO;

import javax.validation.constraints.NotNull;

/**
 * （微信支付三次封装，二次是自己的，一次是wx支付github）
 * 商品支付接口
 */

public interface ProductPayOwnService {

    /**
     * 根据自己的商品id进行创建订单
     *
     * @param productId
     * @return
     */
    CreateOrderVO createProductOrder(String productId) ;

    /**
     * 成功支付回调后改变状态的（商品用的）
     * @param outTradeNo
     * @return
     */
    Boolean successProductNotify(@NotNull String outTradeNo);

    /**
     * 卖家同意退款
     * @param productId
     * @return
     */
    Boolean refund(@NotNull String productId);

    Boolean refundProductNotify(@NotNull String outRefundNo);

    CreateOrderVO repay(@NotNull String productId);

}
