package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.config.CallbackAddressProperties;
import com.example.mapper.PaymentMapper;
import com.example.mapper.RefundMapper;
import com.example.model.dto.CreateOrderDTO;
import com.example.model.dto.CreateRefundDTO;
import com.example.model.entity.Payment;
import com.example.model.entity.Refund;
import com.example.model.entity.Wxuser;
import com.example.model.vo.CreateOrderVO;
import com.example.service.PayOwn;
import com.example.utils.AccountHolder;
import com.example.utils.DateUtils;
import com.example.utils.RandomUtil;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Service
public class PayOwnImpl implements PayOwn {

    @Resource
    CallbackAddressProperties callbackAddressProperties;
    @Resource
    WxPayService wxPayService;

    @Resource
    PaymentMapper paymentMapper;

    @Resource
    RefundMapper refundMapper;

    @Override
    public Payment createOrderByOwnPayInterface(@NotNull CreateOrderDTO createOrderDTO) {

        Wxuser user = AccountHolder.getUser();
        WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();

        orderRequest.setBody(createOrderDTO.getBody());

        orderRequest.setTotalFee(BaseWxPayRequest.yuanToFen(String.valueOf(createOrderDTO.getTotalPrice())));//元转成分

        orderRequest.setOpenid(user.getOpenid());

        orderRequest.setSpbillCreateIp("0.0.0.0");//用户的客户端ip地址

        Date date = new Date();

        String startTime = DateUtils.dateToString(date);
        String endTime = DateUtils.dateToString(DateUtils.dateAddTime(date, 24));
        orderRequest.setTimeStart(startTime);
        orderRequest.setTimeExpire(endTime);

        //16位是支付订单号
        String outTradeNo = RandomUtil.generateRandomNumberString(16);
        orderRequest.setOutTradeNo(outTradeNo);//自动生成的

        orderRequest.setNotifyUrl(callbackAddressProperties.getSuccessAddress());

        orderRequest.setProductId(createOrderDTO.getProductId());

        orderRequest.setTradeType("JSAPI");//这个是商户的参数

        try {

            WxPayMpOrderResult wxPayMpOrderResult = wxPayService.createOrder(orderRequest);

            Payment payment = new Payment();
            BeanUtils.copyProperties(wxPayMpOrderResult, payment);

            payment.setPayer(user.getId());
            payment.setOutTradeNo(outTradeNo);
            payment.setStatusCode("成功下单");
            payment.setStatusNumber(0);
            payment.setProductId(createOrderDTO.getProductId());
            paymentMapper.insert(payment);

            return payment;

        } catch (WxPayException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String queryOrder(String generalId) {
        QueryWrapper<Payment> paymentQueryWrapper = new QueryWrapper<>();

        paymentQueryWrapper.eq("product_id", generalId);

        Payment payment = paymentMapper.selectOne(paymentQueryWrapper);

        try {
            WxPayOrderQueryResult wxPayOrderQueryResult = wxPayService.queryOrder(null, payment.getOutTradeNo());

            boolean equals = wxPayOrderQueryResult.getTradeState().equals(payment.getStatusCode());

            if (!equals) {
                payment.setStatusCode(wxPayOrderQueryResult.getTradeState());

                paymentMapper.updateById(payment);
            }
            return payment.getStatusCode();

        } catch (WxPayException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Boolean refund(CreateRefundDTO createRefundDTO) {

        WxPayRefundRequest wxPayRefundRequest = new WxPayRefundRequest();

        String outRefundNo = RandomUtil.generateRandomNumberString(12);//设置退款编号12位，避免混淆
        wxPayRefundRequest.setOutRefundNo(outRefundNo);//这个是退款单号
        wxPayRefundRequest.setNotifyUrl(callbackAddressProperties.getRefundAddress());//后续再修改，看拿到什么内容 通知地址
        wxPayRefundRequest.setOutTradeNo(createRefundDTO.getOutTradeNo());//原支付订单号
        wxPayRefundRequest.setTotalFee(BaseWxPayRequest.yuanToFen(String.valueOf(createRefundDTO.getTotalFee())));
        wxPayRefundRequest.setRefundFee(BaseWxPayRequest.yuanToFen(String.valueOf(createRefundDTO.getRefundFee())));

        try {

            WxPayRefundResult refundResult = wxPayService.refund(wxPayRefundRequest);

            Refund refund = new Refund();
            BeanUtils.copyProperties(refundResult, refund);

            refund.setStatusNumber(0);//0是申请退款了（不一定成功），1是成功了

            refundMapper.insert(refund);

            //更新payment表的状态
            Payment payment = paymentMapper.selectById(createRefundDTO.getOutTradeNo());
            queryOrder(payment.getProductId());//为了更新payment表中的状态
            payment.setStatusNumber(2);//payment中的状态数字2表示退款成功（0是下单，1是支付成功，2是退款成功）
            paymentMapper.updateById(payment);

            return Boolean.TRUE;
        } catch (WxPayException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void closeOrder(String outTradeNo) {
        try {

            wxPayService.closeOrder(outTradeNo);

            paymentMapper.deleteById(outTradeNo);

        } catch (WxPayException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Payment successNotify(String outTradeNo) {
        QueryWrapper<Payment> paymentQueryWrapper = new QueryWrapper<>();

        paymentQueryWrapper.eq("out_trade_no", outTradeNo).eq("status_number", 0);


        Payment payment = paymentMapper.selectOne(paymentQueryWrapper);

        if (payment == null) {

            throw new RuntimeException("状态错误，或者已经执行，请勿重复支付或提交！");
        }

        //更新操作（更新支付表数据）
        payment.setStatusNumber(1);
        payment.setStatusCode("SUCCESS");
        paymentMapper.updateById(payment);

        return payment;
    }

    @Override
    public Boolean refundNotify(String outRefundNo) {

        Refund refund = refundMapper.selectById(outRefundNo);
        refund.setStatusNumber(1);//1说明回调成功了

        refundMapper.updateById(refund);

        return Boolean.TRUE;
    }

    @Override
    public String getGeneralId(String outTradeNo) {
        Payment payment = paymentMapper.selectById(outTradeNo);

        return payment.getProductId();
    }

    @Override
    public CreateOrderVO rePay(String generalId) {
        QueryWrapper<Payment> paymentQueryWrapper = new QueryWrapper<Payment>().eq("productId", generalId);

        Payment payment = paymentMapper.selectOne(paymentQueryWrapper);
        if (payment==null||payment.getStatusCode().equals("REFUND")||payment.getStatusCode().equals("SUCCESS")){
            throw new RuntimeException("该订单已经支付或退款!");
        }
        CreateOrderVO createOrderVO = new CreateOrderVO();

        BeanUtils.copyProperties(payment,createOrderVO);
        return createOrderVO;
    }
}
