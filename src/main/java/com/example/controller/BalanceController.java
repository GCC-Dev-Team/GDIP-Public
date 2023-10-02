package com.example.controller;

import com.example.common.Result;
import com.example.service.BalanceRecordsService;
import com.example.utils.AccountHolder;
import com.google.common.math.DoubleMath;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 余额控制器，提现，查看我的余额详情信息
 */
@RestController
@RequestMapping("/balance")
public class BalanceController {
    @Resource
    BalanceRecordsService balanceRecordsService;

    /**
     * 查看我的余额记录
     */
    @GetMapping
    Result getMyBalance(){
       return balanceRecordsService.getBalanceDetail();
    }

    /**
     * 查看我当前的余额(在我的个人信息接口也是可以拿去到的，可以通过那个去拿)
     */
    @GetMapping("/myBalance")
    Double myBalance(){
        return AccountHolder.getUser().getBalance();
    }



}
