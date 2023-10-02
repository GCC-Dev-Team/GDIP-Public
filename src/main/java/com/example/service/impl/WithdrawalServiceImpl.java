package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.mapper.WxuserMapper;
import com.example.model.dto.ConfirmBalanceRequest;
import com.example.model.dto.WithdrawalBalanceDTO;
import com.example.model.dto.WithdrawalDTO;
import com.example.model.entity.Withdrawal;
import com.example.model.entity.Wxuser;
import com.example.service.BalanceRecordsService;
import com.example.service.WithdrawalService;
import com.example.mapper.WithdrawalMapper;
import com.example.utils.AccountHolder;
import com.example.utils.RandomUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author L
* @description 针对表【withdrawal】的数据库操作Service实现
* @createDate 2023-10-01 13:20:28
*/

/**
 * 这个是提现的功能
 */
@Service
@Transactional
public class WithdrawalServiceImpl extends ServiceImpl<WithdrawalMapper, Withdrawal>
    implements WithdrawalService{
    @Resource
    BalanceRecordsService balanceRecordsService;
    @Resource
    WxuserMapper wxuserMapper;
    @Override
    public Result withdrawal(WithdrawalDTO withdrawalDTO) {
        Wxuser user = AccountHolder.getUser();
        boolean equals = (user.getBalance()>0)&&(user.getBalance()>= withdrawalDTO.getBalance())&&user.getPasswordPay().equals(withdrawalDTO.getPayPassword());

        if(!equals){
            return Result.failure(ResultCode.SYSTEM_ERROR,"提现密码错误!");
        }
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setWithdrawalId("Withdrawal:"+ RandomUtil.generateRandomString(16));
        withdrawal.setWithdrawalAmount(withdrawalDTO.getBalance());
        withdrawal.setWithdrawalPassword("cash:"+RandomUtil.generateRandomString(16));
        withdrawal.setStatus(0);
        withdrawal.setUserId(user.getId());

        save(withdrawal);

        Boolean payWithdrawal = balanceRecordsService.payWithdrawal(new WithdrawalBalanceDTO(withdrawal.getWithdrawalAmount(), withdrawal.getWithdrawalId(), withdrawal.getUserId()));
        if (Boolean.TRUE.equals(payWithdrawal)){
            user.setBalance(user.getBalance()-withdrawal.getWithdrawalAmount());

            wxuserMapper.updateById(user);

            return Result.success("提现成功，请联系管理员进行转账!密钥："+ withdrawal.getWithdrawalPassword());
        }

       throw new RuntimeException("提现失败!,余额问题!");
    }

    //
    @Override
    public Result cancelWithdrawal(String withdrawalId) {
        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Withdrawal> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("withdrawal_id", withdrawalId).eq("status",0);
        Withdrawal withdrawal = getOne(queryWrapper);

        if (withdrawal==null){

            return Result.failure(ResultCode.SYSTEM_ERROR,"该提现申请中或已经到账！");
        }

        Boolean cancelWithdrawal = balanceRecordsService.cancelWithdrawal(new WithdrawalBalanceDTO(withdrawal.getWithdrawalAmount(), withdrawal.getWithdrawalId(), withdrawal.getUserId()));

        if (Boolean.TRUE.equals(cancelWithdrawal)){
            user.setBalance(user.getBalance()+withdrawal.getWithdrawalAmount());

            withdrawal.setStatus(2);

            updateById(withdrawal);

            wxuserMapper.updateById(user);

            return Result.success("取消提现成功!");
        }

        throw new RuntimeException("取消提现失败!,余额问题!");
    }

    @Override
    public Boolean confirmBalance(ConfirmBalanceRequest confirmBalanceRequest) {

        if (confirmBalanceRequest.getUserName().equals("小贺")&&confirmBalanceRequest.getConfirmPassword().equals("123456")){

            QueryWrapper<Withdrawal> wrapper = new QueryWrapper<Withdrawal>().eq("status", 0).
                    eq("withdrawal_id", confirmBalanceRequest.getWithdrawalId()).
                    eq("withdrawal_password", confirmBalanceRequest.getWithdrawalPassword());
            Withdrawal byId = getOne(wrapper);

            if (byId==null){
                throw new RuntimeException("找不到该订单号!");
            }

            byId.setStatus(1);

            updateById(byId);

            return Boolean.TRUE;
        }

        throw new RuntimeException("用户名和密码错误!");
    }
}




