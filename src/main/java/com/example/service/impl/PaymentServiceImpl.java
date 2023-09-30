package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.model.entity.Payment;
import com.example.service.PaymentService;
import com.example.mapper.PaymentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author L
* @description 针对表【payment】的数据库操作Service实现
* @createDate 2023-08-14 17:26:12
*/
@Service
@Transactional
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment>
    implements PaymentService{

}




