package com.example.controller;

import com.example.common.Result;
import com.example.service.WxPayOwnService;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/wxpay")
@Slf4j
public class PayController {

    @Resource
    WxPayOwnService wxPayOwnService;

    @Resource
    WxPayService wxPayService;
    /**
     * 回调使用
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @PostMapping("/notify")
    public String payNotify(HttpServletRequest request, HttpServletResponse response) {

        System.out.println(request);
        System.out.printf("这里是Post请求拿到的信息");
        try {
            String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            WxPayOrderNotifyResult result = wxPayService.parseOrderNotifyResult(xmlResult);

            // 加入自己处理订单的业务逻辑，需要判断订单是否已经支付过，否则可能会重复调用
            String orderId = result.getOutTradeNo();
            String tradeNo = result.getTransactionId();
            String totalFee = BaseWxPayResult.fenToYuan(result.getTotalFee());

            //自己的业务
//            result.getTrade
            return WxPayNotifyResponse.success("处理成功!");
        } catch (Exception e) {
            log.error("微信回调结果异常,异常原因{}", e.getMessage());
            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }

    /**
     * Get请求回调
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/notify")
    public String payNotify2(HttpServletRequest request, HttpServletResponse response) {

        System.out.println(request);
        System.out.printf("这里是GET请求拿到的信息");
        try {
            String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            WxPayOrderNotifyResult result = wxPayService.parseOrderNotifyResult(xmlResult);

            // 加入自己处理订单的业务逻辑，需要判断订单是否已经支付过，否则可能会重复调用
            String orderId = result.getOutTradeNo();
            String tradeNo = result.getTransactionId();
            String totalFee = BaseWxPayResult.fenToYuan(result.getTotalFee());

            //自己的业务
//            result.getTrade
            return WxPayNotifyResponse.success("处理成功!");
        } catch (Exception e) {
            log.error("微信回调结果异常,异常原因{}", e.getMessage());
            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }

    /**
     * 根据产品id号下单
     * @param productId
     * @return
     * @throws WxPayException
     */

    @PostMapping("/addTrade")
    public Result addTrade(@NotNull String productId) throws WxPayException {

        WxPayMpOrderResult order = wxPayOwnService.createOrder(productId);

        return Result.success(order);
    }

    /**
     * 根据产品号查询订单情况
     * @param produnctId
     * @return
     */
    @GetMapping("/trade")
    public Result queryTrade(@NotNull String produnctId){

        String s = wxPayOwnService.queryOrder(produnctId);

        return Result.success(s);
    }






}
