package com.example.controller;

import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.model.vo.CreateOrderVO;
import com.example.service.PayOwn;
import com.example.service.ProductPayOwnService;
import com.github.binarywang.wxpay.exception.WxPayException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 商品支付
 */
@RestController
@RequestMapping("/productPay")
public class ProductPayController {
    @Resource
    ProductPayOwnService productPayOwnService;
    @Resource
    PayOwn payOwn;

    /**
     * 根据产品id号下单（下单购买）
     *
     * @param productId
     * @return
     * @throws WxPayException
     */

    @PostMapping("/addTrade")
    public Result addTrade(@NotNull String productId) {

        CreateOrderVO order = productPayOwnService.createProductOrder(productId);

        if (order==null){
            return Result.failure(ResultCode.PARAM_IS_INVALID,"该商品为本人发布，请勿下单!");
        }
        return Result.success(order);
    }

    /**
     * 根据产品号查询订单情况（查询是否付款）
     *
     * @param productId 商品id号码
     * @return
     */
    @GetMapping("/trade")
    public Result queryTrade(@NotNull String productId) {

        return Result.success(payOwn.queryOrder(productId));
    }

    /**
     * 重新支付
     */
    @PostMapping("/rePay")
    CreateOrderVO rePay(@NotNull String productId){

        return payOwn.rePay(productId);
    }

}
