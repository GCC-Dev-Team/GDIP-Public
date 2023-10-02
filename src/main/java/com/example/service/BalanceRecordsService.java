package com.example.service;

import com.example.common.Result;
import com.example.model.dto.BalanceReceiveAndPayDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.dto.WithdrawalBalanceDTO;
import com.example.model.entity.BalanceRecords;

import javax.validation.constraints.NotNull;

/**
* @author L
* @description 针对表【balance_records】的数据库操作Service
* @createDate 2023-10-01 13:20:34
*/
public interface BalanceRecordsService extends IService<BalanceRecords> {
    /**
     * 打款进入钱包（任务或者商城交易后）也就是收入（现在只搞收入,这个是不涉及提现的，）
     * 记得用户表的余额也要更新（如果是+的话意味着这个是收款，userid是收款方面
     * 如果是-的话，这个是打款，这个是userid是付款者，也就是用户支付自己，payment的支付者）
     * 0是+，1是-
     * 钱包余额记录，可以支付(暂时没开发)
     */
    Boolean payOrReceive(@NotNull BalanceReceiveAndPayDTO balanceReceiveAndPayDTO);

    /**
     * 申请提现对接的接口（status是1）
     */
    Boolean payWithdrawal(@NotNull WithdrawalBalanceDTO withdrawalBalanceDTO);


    /**
     * 取消提现接口(要查看是否有这个余额扣除，有的话再加上去)（status是0）
     */
    Boolean cancelWithdrawal(@NotNull WithdrawalBalanceDTO withdrawalBalanceDTO);
    /**
     * 查询我的余额详细
     */
    Result getBalanceDetail();




}
