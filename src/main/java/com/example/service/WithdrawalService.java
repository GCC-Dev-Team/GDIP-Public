package com.example.service;

import com.example.common.Result;
import com.example.model.dto.WithDrawalDTO;
import com.example.model.entity.Withdrawal;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author L
* @description 针对表【withdrawal】的数据库操作Service
* @createDate 2023-10-01 13:20:28
*/
/**
 * 这个是提现的功能
 */
public interface WithdrawalService extends IService<Withdrawal> {

    Result withDrawal(WithDrawalDTO withDrawalDTO);

    Result cancelWithDrawal(String withdrawalId);


}
