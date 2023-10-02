package com.example.service;

import com.example.common.Result;
import com.example.model.dto.ConfirmBalanceRequest;
import com.example.model.dto.WithdrawalDTO;
import com.example.model.entity.Withdrawal;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;

/**
* @author L
* @description 针对表【withdrawal】的数据库操作Service
* @createDate 2023-10-01 13:20:28
*/
/**
 * 这个是提现的功能
 */
public interface WithdrawalService extends IService<Withdrawal> {

    /**
     * 提现
     * @param withdrawalDTO
     * @return
     */
    Result withdrawal(WithdrawalDTO withdrawalDTO);

    /**
     * 取消提现
     * @param withdrawalId
     * @return
     */
    Result cancelWithdrawal(String withdrawalId);

    /**
     * 后台接口，确认打款
     */
    Boolean confirmBalance(@NotNull @RequestBody ConfirmBalanceRequest confirmBalanceRequest);

}
