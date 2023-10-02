package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.mapper.BalanceRecordsMapper;
import com.example.mapper.PaymentMapper;
import com.example.mapper.WxuserMapper;
import com.example.model.dto.BalanceReceiveAndPayDTO;
import com.example.model.dto.WithdrawalBalanceDTO;
import com.example.model.entity.BalanceRecords;
import com.example.model.entity.Payment;
import com.example.model.entity.Wxuser;
import com.example.model.vo.BalanceDetailVO;
import com.example.service.BalanceRecordsService;
import com.example.utils.AccountHolder;
import com.example.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author L
* @description 针对表【balance_records】的数据库操作Service实现
* @createDate 2023-10-01 13:20:34
*/
@Service
@Slf4j
@Transactional
public class BalanceRecordsServiceImpl extends ServiceImpl<BalanceRecordsMapper, BalanceRecords>
    implements BalanceRecordsService{
    @Resource
    PaymentMapper paymentMapper;
    @Resource
    WxuserMapper wxuserMapper;
    @Resource
    BalanceRecordsMapper balanceRecordsMapper;

    /**
     * 打款进入钱包（任务或者商城交易后）也就是收入（现在只搞收入,这个是不涉及提现的，）
     * 记得用户表的余额也要更新（如果是+的话意味着这个是收款，userid是收款方面
     * 如果是-的话，这个是打款，这个是userid是付款者，也就是用户支付自己，payment的支付者）
     * 0是+，1是-
     */
    @Override
    public Boolean payOrReceive(BalanceReceiveAndPayDTO balanceReceiveAndPayDTO) {
        if (balanceReceiveAndPayDTO.getBalanceType().equals(0)){

            Payment payment = paymentMapper.selectById(balanceReceiveAndPayDTO.getPaymentOrderId());

            Wxuser wxuser = wxuserMapper.selectById(payment.getRecipient());



            boolean equals = payment.getStatusNumber().equals(1);

            if (!equals){
                //不等于true，那就意味着支付表的支付没有支付成功
                throw new RuntimeException("支付没有支付成功");

            }else {

                BalanceRecords balanceRecords = new BalanceRecords();
                balanceRecords.setBalanceId("balance:"+ RandomUtil.generateRandomString(12));
                balanceRecords.setUserId(wxuser.getId());
                balanceRecords.setCurrentBalance(wxuser.getBalance()+balanceReceiveAndPayDTO.getBalanceChange());
                BeanUtils.copyProperties(balanceReceiveAndPayDTO,balanceRecords);

                wxuser.setBalance(balanceRecords.getCurrentBalance());
                save(balanceRecords);
                wxuserMapper.updateById(wxuser);

                return Boolean.TRUE;
            }

        }else {
            //如果需要加入余额支付请在这里修改，开通就行,不过payment也要改
            log.error("余额支付系统展示没有开启");
            throw new RuntimeException("余额支付系统展示没有开启");
        }
    }

    /**
     * 获取我详细的余额信息
     * @return
     */
    @Override
    public Result getBalanceDetail() {
        Wxuser user = AccountHolder.getUser();

        List<BalanceRecords> myBalanceDetail = balanceRecordsMapper.selectList(new QueryWrapper<BalanceRecords>().eq("user_id", user.getId()));

        List<BalanceDetailVO> balanceDetailVOS = new ArrayList<>();

        if (myBalanceDetail.isEmpty()){
            return Result.success(balanceDetailVOS);
        }


        balanceDetailVOS = myBalanceDetail.stream().
                map(balanceRecords -> new BalanceDetailVO(balanceRecords.getCurrentBalance(),
                balanceRecords.getBalanceType(),
                balanceRecords.getBalanceChange())).toList();

        return Result.success(balanceDetailVOS);
    }

    /**
     * 提现操作
     * @param withdrawalBalanceDTO
     * @return
     */

    @Override
    public Boolean payWithdrawal(WithdrawalBalanceDTO withdrawalBalanceDTO) {
        Wxuser user = AccountHolder.getUser();
        if (!withdrawalBalanceDTO.getUserId().equals(user.getId())){
            throw new RuntimeException("账号异常，请登录!");
        }
        BalanceRecords balanceRecords = new BalanceRecords();
        //注意提现是-
        balanceRecords.setCurrentBalance(user.getBalance()-withdrawalBalanceDTO.getBalanceChange());
        balanceRecords.setBalanceId("balance:"+RandomUtil.generateRandomString(15));
        balanceRecords.setBalanceChange(withdrawalBalanceDTO.getBalanceChange());
        balanceRecords.setBalanceType(1);
        balanceRecords.setUserId(withdrawalBalanceDTO.getUserId());
        balanceRecords.setPaymentOrderId(withdrawalBalanceDTO.getWithdrawalId());

        save(balanceRecords);
        return Boolean.TRUE;
    }

    @Override
    public Boolean cancelWithdrawal(WithdrawalBalanceDTO withdrawalBalanceDTO) {

        Wxuser user = AccountHolder.getUser();
        if (!withdrawalBalanceDTO.getUserId().equals(user.getId())){
            throw new RuntimeException("账号异常，请登录!");
        }

        BalanceRecords balanceRecords = new BalanceRecords();
        //注意提现是-
        balanceRecords.setCurrentBalance(user.getBalance()+withdrawalBalanceDTO.getBalanceChange());
        balanceRecords.setBalanceId("balance:"+RandomUtil.generateRandomString(15));
        balanceRecords.setBalanceChange(withdrawalBalanceDTO.getBalanceChange());
        balanceRecords.setBalanceType(0);
        balanceRecords.setUserId(withdrawalBalanceDTO.getUserId());
        balanceRecords.setPaymentOrderId(withdrawalBalanceDTO.getWithdrawalId());

        save(balanceRecords);
        return Boolean.TRUE;
    }
}




