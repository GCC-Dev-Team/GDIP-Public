package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.model.entity.Withdrawal;
import com.example.service.WithdrawalService;
import com.example.mapper.WithdrawalMapper;
import org.springframework.stereotype.Service;

/**
* @author L
* @description 针对表【withdrawal】的数据库操作Service实现
* @createDate 2023-10-01 13:20:28
*/
@Service
public class WithdrawalServiceImpl extends ServiceImpl<WithdrawalMapper, Withdrawal>
    implements WithdrawalService{

}




