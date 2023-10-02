package com.example.controller;

import com.example.anno.NoNeedLogin;
import com.example.common.Result;
import com.example.model.dto.ConfirmBalanceRequest;
import com.example.model.dto.WithdrawalBalanceDTO;
import com.example.model.dto.WithdrawalDTO;
import com.example.service.WithdrawalService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 提现控制
 */
@RestController
@RequestMapping("/Withdrawal")
public class WithdrawalController {
    @Resource
    WithdrawalService withdrawalService;

    /**
     * 申请提现
     * @param withdrawalDTO
     * @return
     */
    @PostMapping
    Result withdrawal(@NotNull @RequestBody WithdrawalDTO withdrawalDTO){

        return withdrawalService.withdrawal(withdrawalDTO);
    }
    /**
     * 取消提现
     */
    @DeleteMapping
    Result cancelWithdrawal(@NotNull String withdrawalId){

        return withdrawalService.cancelWithdrawal(withdrawalId);
    }

    /**
     * 后台接口，手动打款后调用
     */
    @NoNeedLogin
    @PostMapping("/confirmBalance")
    Boolean confirmBalance(@NotNull@RequestBody ConfirmBalanceRequest confirmBalanceRequest){
        return withdrawalService.confirmBalance(confirmBalanceRequest);
    }


}
