package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.PaymentMapper;
import com.example.mapper.ProductMapper;
import com.example.mapper.RefundMapper;
import com.example.model.entity.Payment;
import com.example.model.entity.Product;
import com.example.model.entity.Refund;
import com.example.model.entity.Wxuser;
import com.example.service.ProductPayOwnService;
import com.example.utils.AccountHolder;
import com.example.utils.DateUtils;
import com.example.utils.TimerUtils;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

@Service
public class ProductPayOwnServiceImpl implements ProductPayOwnService {

    private static final String NOTIFY_URL = "https://xiaoligongzuoshi.top/wxpay/notify";//回调地址

    private static final String REFUND_NOTIFY_URL = "https://xiaoligongzuoshi.top/wxpay/refundNotify";//回调地址
    @Resource
    ProductMapper productMapper;

    @Resource
    PaymentMapper paymentMapper;

    @Resource
    RefundMapper refundMapper;

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

        TimerUtils.scheduleTask(()->{

            String s = queryOrder(productId);

            if(!s.equals("SUCCESS")){

                //如果未支付的操作
                try {
                    wxPayService.closeOrder(outTradeNo);

                    product.setProductStatus(0);

                    productMapper.updateById(product);

                    paymentMapper.deleteById(payment);

                } catch (WxPayException e) {

                    throw new RuntimeException(e);
                }
            }
        });

        return wxPayMpOrderResult;
    }

    @Override
    public String queryOrder(String productId) {

        QueryWrapper<Payment> paymentQueryWrapper = new QueryWrapper<>();

        paymentQueryWrapper.eq("product_id", productId);

        Payment payment = paymentMapper.selectOne(paymentQueryWrapper);

        try {
            WxPayOrderQueryResult wxPayOrderQueryResult = wxPayService.queryOrder(null, payment.getId());

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
     * @param outTradeNo
     * @return
     */
    @Override
    public Boolean successNotify(String outTradeNo) {

        QueryWrapper<Payment> paymentQueryWrapper = new QueryWrapper<>();

        paymentQueryWrapper.eq("id", outTradeNo).eq("status_number", 0);


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

    @Override
    public Boolean refund(String productId) {

        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Payment> paymentQueryWrapper = new QueryWrapper<>();
        paymentQueryWrapper.eq("product_id",productId).eq("status_code","SUCCESS").eq("seller",user.getId());
        Payment payment = paymentMapper.selectOne(paymentQueryWrapper);

        if(payment==null){
            throw new RuntimeException("该订单找不到或者该订单未支付!");
        }

        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("product_id",productId).eq("product_status",4);//后续加入
        Product product = productMapper.selectOne(productQueryWrapper);

        WxPayRefundRequest wxPayRefundRequest = new WxPayRefundRequest();
        String outRefundNo=generateRandomNumber();
        wxPayRefundRequest.setOutRefundNo(outRefundNo);//这个是退款单号
        wxPayRefundRequest.setNotifyUrl(REFUND_NOTIFY_URL);//后续再修改，看拿到什么内容 通知地址
        wxPayRefundRequest.setOutTradeNo(payment.getId());//原支付订单号
        wxPayRefundRequest.setTotalFee(BaseWxPayRequest.yuanToFen(String.valueOf(product.getProductPrice())));
        wxPayRefundRequest.setRefundFee(BaseWxPayRequest.yuanToFen(String.valueOf(product.getProductPrice())));


        try {
            WxPayRefundResult refund = wxPayService.refund(wxPayRefundRequest);

            queryOrder(productId);//为了更新payment表中的状态
            payment.setStatusNumber(2);//payment中的状态数字2表示退款成功

            Refund refundSql = new Refund();
            refundSql.setOutRefundNo(refund.getOutRefundNo());
            refundSql.setOutTradeNo(refund.getOutTradeNo());
            refundSql.setTotalFee(refund.getTotalFee());
            refundSql.setRefundFee(refund.getRefundFee());
            refundSql.setStatusCode(refund.getResultCode());
            refundSql.setStatusNumber(0);//0是申请退款了（不一定成功），1是成功了

            product.setProductStatus(0);

            refundMapper.insert(refundSql);
            productMapper.updateById(product);
            paymentMapper.updateById(payment);

            return Boolean.TRUE;

        } catch (WxPayException e) {

            throw new RuntimeException(e);
        }

    }

    @Override
    public Boolean refundNotify(String outRefundNo) {

        Refund refund = refundMapper.selectById(outRefundNo);
        refund.setStatusNumber(1);//1说明回调成功了

        refundMapper.updateById(refund);

        return Boolean.TRUE;
    }
}
