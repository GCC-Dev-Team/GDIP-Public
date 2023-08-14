package com.example.service;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;


public interface WxPayOwnService {

    /**
     * 根据自己的商品id进行创建订单
     * @param productId
     * @return
     */
    WxPayMpOrderResult createOrder(String productId) throws WxPayException;

    String queryOrder(String productId);


}
