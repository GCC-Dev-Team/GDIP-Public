package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.Result;
import com.example.mapper.PaymentMapper;
import com.example.mapper.ProductMapper;
import com.example.model.entity.Payment;
import com.example.model.entity.Product;
import com.example.model.entity.Wxuser;
import com.example.service.WxPayOwnService;
import com.example.utils.AccountHolder;
import com.example.utils.DateUtils;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

@Service
public class WxPayOwnServiceImpl implements WxPayOwnService {

    private static final String NOTIFY_URL = "https://xiaoligongzuoshi.top/wxpay/notify";//回调地址
    @Resource
    ProductMapper productMapper;

    @Resource
    PaymentMapper paymentMapper;

    @Resource
    WxPayService wxPayService;

    @Override
    public WxPayMpOrderResult createOrder(String productId) throws WxPayException {

        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("product_status", 0).eq("product_id", productId);

        Product product = productMapper.selectOne(productQueryWrapper);

        Wxuser user = AccountHolder.getUser();

        if (product == null) {

            throw new RuntimeException("商品不存在或者商品已经出售!");
        }

        WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();

        orderRequest.setBody("轻小南商城商品");

        orderRequest.setTotalFee(BaseWxPayRequest.yuanToFen(String.valueOf(product.getProductPrice())));//元转成分

        orderRequest.setOpenid(user.getOpenid());

        orderRequest.setSpbillCreateIp("0.0.0.0");//用户的客户端ip地址

        Date date = new Date();

        String startTime = DateUtils.dateToString(date);
        String endTime = DateUtils.dateToString(DateUtils.dateAddTime(date, 24));
        orderRequest.setTimeStart(startTime);
        orderRequest.setTimeExpire(endTime);

        String outTradeNo = generateRandomNumber();
        orderRequest.setOutTradeNo(outTradeNo);//自动生成的

        orderRequest.setNotifyUrl(NOTIFY_URL);

        orderRequest.setProductId(product.getProductId());

        orderRequest.setTradeType("JSAPI");//这个是商户的参数

        WxPayMpOrderResult wxPayMpOrderResult = wxPayService.createOrder(orderRequest);

        Payment payment = new Payment();

        payment.setId(outTradeNo);
        payment.setBuyer(user.getId());
        payment.setSeller(product.getPublisherId());
        payment.setPaySign(wxPayMpOrderResult.getPaySign());
        payment.setNonceStr(wxPayMpOrderResult.getNonceStr());
        payment.setPrepayId(wxPayMpOrderResult.getPackageValue());
        payment.setSignType(wxPayMpOrderResult.getSignType());
        payment.setProductId(product.getProductId());
        payment.setTimeStamp(wxPayMpOrderResult.getTimeStamp());

        payment.setStatusCode("成功下单");
        payment.setStatusNumber(0);

        paymentMapper.insert(payment);

        product.setProductStatus(2);//更换商品的状态码

        productMapper.updateById(product);

        return wxPayMpOrderResult;
    }

    @Override
    public String queryOrder(String productId) {

        QueryWrapper<Payment> paymentQueryWrapper = new QueryWrapper<>();

        paymentQueryWrapper.eq("product_id", productId);

        Payment payment = paymentMapper.selectOne(paymentQueryWrapper);

        try {
            WxPayOrderQueryResult wxPayOrderQueryResult = wxPayService.queryOrder(null, payment.getId());

            boolean equals = wxPayOrderQueryResult.getTradeStateDesc().equals(payment.getStatusCode());

            if (!equals) {
                payment.setStatusCode(wxPayOrderQueryResult.getTradeStateDesc());

                paymentMapper.updateById(payment);
            }
            return payment.getStatusCode();
        } catch (WxPayException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateRandomNumber() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(16);

        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10); // Generate a random digit between 0 and 9
            stringBuilder.append(digit);
        }

        return stringBuilder.toString();
    }

    /**
     * 成功回调后（成功支付后的业务层）
     *
     * @param id
     * @return
     */
    @Override
    public Boolean successNotify(String id) {

        QueryWrapper<Payment> paymentQueryWrapper = new QueryWrapper<>();

        paymentQueryWrapper.eq("id", id).eq("status_number", 0);


        Payment payment = paymentMapper.selectOne(paymentQueryWrapper);

        if (payment == null) {

            throw new RuntimeException("状态错误，或者已经执行，请勿重复支付或提交！");
        }

        //更新操作（更新支付表数据）
        payment.setStatusNumber(1);
        payment.setStatusCode("SUCCESS");
        paymentMapper.updateById(payment);

        //更新product的信息
        Product product = productMapper.selectById(payment.getProductId());
        product.setProductStatus(3);
        productMapper.updateById(product);

        return Boolean.TRUE;

    }
}
