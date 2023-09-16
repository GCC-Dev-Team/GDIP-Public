package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.TaskMapper;
import com.example.model.dto.CreateOrderDTO;
import com.example.model.entity.Payment;
import com.example.model.entity.Task;
import com.example.model.vo.CreateOrderVO;
import com.example.service.PayOwn;
import com.example.service.TaskPayOwnService;
import com.example.utils.TimerUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class TaskPayOwnServiceImpl implements TaskPayOwnService {
    @Resource
    PayOwn payOwn;
    @Resource
    TaskMapper taskMapper;

    @Override
    public CreateOrderVO createTaskOrder(String taskId) {

        //（0开始接单（已经支付了），1是结束（钱已经付款给接单者），3是未支付，算是暂时保存了（是已经创建了订单了）5:是没有调用微信订单）
        Task task = taskMapper.selectOne(new QueryWrapper<Task>().eq("id", taskId).eq("status", 5));

        if (task == null) {

            throw new RuntimeException("请检查该任务订单是否已经支付或者任务订单号是否正确！");
        }
        Payment payment = payOwn.createOrderByOwnPayInterface(new CreateOrderDTO(taskId, "广小轻任务订单", task.getPrice()));

        task.setStatus(3);
        taskMapper.updateById(task);

        TimerUtils.scheduleTask(() -> {

            String s = payOwn.queryOrder(taskId);

            if (!s.equals("SUCCESS")) {

                //如果未支付的操作
                payOwn.closeOrder(payment.getOutTradeNo());

                task.setStatus(5);

                taskMapper.updateById(task);
            }
        });

        CreateOrderVO createOrderVO = new CreateOrderVO();
        BeanUtils.copyProperties(payment, createOrderVO);

        return createOrderVO;
    }

    @Override
    public Boolean successTaskNotify(String outTradeNo) {
        Payment payment = payOwn.successNotify(outTradeNo);

        //更新自己的任务状态（自己的业务）

        Task task = taskMapper.selectById(payment.getProductId());

        task.setStatus(0);
        taskMapper.updateById(task);

        return Boolean.TRUE;
    }

    @Override
    public Boolean refundTaskNotify(String outRefundNo) {

        return payOwn.refundNotify(outRefundNo);
    }

    @Override
    public CreateOrderVO rePay(String taskId) {
        return payOwn.rePay(taskId);
    }
}
