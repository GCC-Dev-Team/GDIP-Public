package com.example.service;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;

import javax.validation.constraints.NotNull;


public interface ProductPayOwnService {

    /**
     * 根据自己的商品id进行创建订单
     * @param productId
     * @return
     */
    WxPayMpOrderResult createOrder(String productId) throws WxPayException;

    String queryOrder(String productId);

    Boolean successNotify(@NotNull String outTradeNo);

    Boolean refund(@NotNull String productId);

    Boolean refundNotify(@NotNull String outRefundNo);

}
