package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.PaymentMapper;
import com.example.mapper.ProductMapper;
import com.example.mapper.RefundMapper;
import com.example.model.dto.CreateOrderDTO;
import com.example.model.dto.CreateRefundDTO;
import com.example.model.entity.Payment;
import com.example.model.entity.Product;

import com.example.model.entity.Wxuser;
import com.example.model.vo.CreateOrderVO;
import com.example.service.PayOwn;
import com.example.service.ProductPayOwnService;
import com.example.utils.AccountHolder;
import com.example.utils.TimerUtils;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductPayOwnServiceImpl implements ProductPayOwnService {


    @Resource
    ProductMapper productMapper;

    @Resource
    PaymentMapper paymentMapper;

    @Resource
    PayOwn payOwn;

    @Override
    public CreateOrderVO createProductOrder(String productId) {

        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("product_status", 0).eq("product_id", productId);

        Product product = productMapper.selectOne(productQueryWrapper);

        if (product == null) {

            throw new RuntimeException("商品不存在或者商品已经出售!");
        }

        Payment payment = payOwn.createOrderByOwnPayInterface(new CreateOrderDTO(product.getProductId(), "轻小南二手商城", product.getProductPrice()));

        payment.setRecipient(product.getPublisherId());//设置收款方（二手商城是卖家）
        paymentMapper.updateById(payment);

        product.setProductStatus(2);//更换商品的状态码
        productMapper.updateById(product);

        TimerUtils.scheduleTask(() -> {

            String s = payOwn.queryOrder(productId);

            if (!s.equals("SUCCESS")) {

                //如果未支付的操作
                payOwn.closeOrder(payment.getOutTradeNo());

                product.setProductStatus(0);

                productMapper.updateById(product);

            }
        });

        CreateOrderVO createOrderVO = new CreateOrderVO();
        BeanUtils.copyProperties(payment,createOrderVO);

        return createOrderVO;
    }

    /**
     * 成功回调后（成功支付后的业务层）
     *
     * @param outTradeNo
     * @return
     */
    @Override
    public Boolean successProductNotify(String outTradeNo) {

        Payment payment = payOwn.successNotify(outTradeNo);

        //更新product的信息
        Product product = productMapper.selectById(payment.getProductId());
        product.setProductStatus(3);
        productMapper.updateById(product);

        return Boolean.TRUE;
    }

    /**
     * 同意退款
     * @param productId
     * @return
     */
    @Override
    public Boolean refund(String productId) {

        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Payment> paymentQueryWrapper = new QueryWrapper<>();
        paymentQueryWrapper.eq("product_id", productId).eq("status_code", "SUCCESS").eq("recipient", user.getId());
        Payment payment = paymentMapper.selectOne(paymentQueryWrapper);

        if (payment == null) {
            throw new RuntimeException("该订单找不到或者该订单未支付!");
        }

        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("product_id", productId).eq("product_status", 4);//后续加入
        Product product = productMapper.selectOne(productQueryWrapper);

        if(product==null){
            throw new RuntimeException("商品错误!");
        }
        payOwn.refund(new CreateRefundDTO(payment.getOutTradeNo(),product.getProductPrice(),product.getProductPrice()));

        //更新商品
        product.setProductStatus(0);
        productMapper.updateById(product);

        return Boolean.TRUE;
    }

    @Override
    public Boolean refundProductNotify(String outRefundNo) {

        return payOwn.refundNotify(outRefundNo);
    }

    @Override
    public CreateOrderVO repay(String productId) {
        return payOwn.rePay(productId);
    }
}
