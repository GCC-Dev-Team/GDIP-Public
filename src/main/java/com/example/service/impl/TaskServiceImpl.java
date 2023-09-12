package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.mapper.LinkTaskMapper;
import com.example.mapper.PaymentMapper;
import com.example.mapper.WxuserMapper;
import com.example.model.dto.*;
import com.example.model.entity.LinkTask;
import com.example.model.entity.Payment;
import com.example.model.entity.Task;
import com.example.model.entity.Wxuser;
import com.example.model.vo.PageVO;
import com.example.model.vo.TaskDescribeVO;
import com.example.model.vo.TaskSmallVO;
import com.example.service.LinkTaskService;
import com.example.service.PayOwn;
import com.example.service.TaskService;
import com.example.mapper.TaskMapper;
import com.example.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.*;


/**
* @author L
* @description 针对表【task】的数据库操作Service实现
* @createDate 2023-07-31 10:56:35
*/
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task>
    implements TaskService{
    @Resource
    TaskMapper taskMapper;

    @Resource
    WxuserMapper wxuserMapper;
    @Resource
    PaymentMapper paymentMapper;

    @Resource
    LinkTaskService linkTaskService;
    @Resource
    PayOwn payOwn;

    @Resource
    LinkTaskMapper linkTaskMapper;

    /**
     * 创建任务，状态是5，还没有支付下单
     * @param taskCreateRequest
     * @return
     */
    @Override
    public Result createTask(TaskCreateRequest taskCreateRequest) {

        Wxuser user = AccountHolder.getUser();

        Date dateStart = DateUtils.stringToDate(taskCreateRequest.getStartTime());

        Date dateEnd=DateUtils.stringToDate(taskCreateRequest.getEndTime());

        String taskId="task:"+RandomUtil.generateRandomString(18);

        String singOut=RandomUtil.generateRandomNumberString(6);

        Task task = new Task();
        task.setId(taskId);
        task.setInitiator(user.getId());
        //（0开始接单（已经支付了），1是结束（钱已经付款给接单者），3是未支付，算是暂时保存了（是已经创建了订单了）5:是没有调用微信订单）
        task.setStatus(5);
        task.setStartTime(dateStart);
        task.setEndTime(dateEnd);
        task.setSignOutCode(singOut);

        BeanUtils.copyProperties(taskCreateRequest,task);

        this.save(task);

        return Result.success(task);
    }

    @Override
    public Result getTaskById(GetTaskIdRequest getTaskIdRequest) {

        String id=getTaskIdRequest.getId();

        if(id.isEmpty()){
            return Result.failure(ResultCode.TASK_NULL_ID);
        }

        Wxuser user = AccountHolder.getUser();

        Task task = getById(id);

        TaskDescribeVO taskVO = new TaskDescribeVO();

        Wxuser wxuser = wxuserMapper.selectById(task.getInitiator());

        BeanUtils.copyProperties(task,taskVO);

        taskVO.setUserId(wxuser.getId());
        taskVO.setUserImage(wxuser.getAvatar());
        taskVO.setUserName(wxuser.getUserName());

        if(task.getInitiator().equals(user.getId())){
            //这个是自己发布查看的任务详情
            taskVO.setSignOutCode(task.getSignOutCode());
        }
        return Result.success(taskVO);
    }

    /**
     * 获取我发布的任务
     * @return
     */
    @Override
    public Result getTaskSmall(@NotNull @RequestBody PageRequest pageRequest) {

        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();

        taskQueryWrapper.eq("initiator",user.getId())
                .orderByDesc("updated_time");

        Page<Task> taskPage = taskMapper.selectPage(new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize()), taskQueryWrapper);

        return getResult(taskPage);
    }

    @Override
    public Result getAllTask(PageRequest pageRequest) {

        QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();

        taskQueryWrapper.ge("start_time",new Date())
                .orderByDesc("updated_time")
                .eq("status",0);

        Page<Task> taskPage = taskMapper.selectPage(new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize()), taskQueryWrapper);

        return getResult(taskPage);
    }

    @NotNull
    private Result getResult(Page<Task> taskPage) {
        List<Task> tasks = taskPage.getRecords();

        List<TaskSmallVO> taskSmallVOList =null;

        if(!tasks.isEmpty()){

            taskSmallVOList =tasks.stream()
                    .map(task -> new TaskSmallVO(
                            task.getId(),
                            task.getActivityTitle(),
                            task.getImageUrl(),
                            task.getPrice(),
                            task.getStartTime(),
                            task.getEndTime(),
                            task.getPeople(),
                            task.getStatus()
                    )).toList();
        }

        return Result.success(new PageVO<>(taskSmallVOList, taskPage.getTotal(), taskPage.getSize(), taskPage.getCurrent()));
    }

    /**
     * 接单任务（对接单员来说）
     * @param participateTaskRequest
     * @return
     */
    @Override
    public Result participateTask(ParticipateTaskRequest participateTaskRequest) {

        Wxuser user = AccountHolder.getUser();

        String taskId=participateTaskRequest.getId();

        List<String> myNoSingOutTasks=linkTaskService.getMyNoSingOutTask(user.getId());

        Task task = taskMapper.selectById(taskId);

        //查看人数（初期限制了一人）
        if(taskMapper.selectById(taskId).getNumberOfParticipants()<=task.getPeople()||task.getStatus().equals(1)){

            return Result.failure(ResultCode.TASK_ERROR_NUMBER_PEOPLE);
        }
        if(!myNoSingOutTasks.isEmpty()){

            Date taskIdBeginTime=task.getStartTime();
            Date taskIdEndTime=task.getEndTime();

            //这里是有任务没有签退的，看时间是否冲突

            for (String taskIdTemple : myNoSingOutTasks) {

                if (Objects.equals(taskIdTemple, taskId)) {

                    return Result.failure(ResultCode.TASK_ERROR_REPEAT);
                }

                Task taskTemple = taskMapper.selectById(taskIdTemple);

                Date taskTempleBeginTime = taskTemple.getStartTime();
                Date taskTempleEndTime = taskTemple.getEndTime();

                //检测时间是否有重叠的部分
                Boolean isOver = TimeOverlapExample.isTimeOverlap(DateTranslation.DateTranslationLocalDateTime(taskIdBeginTime),
                        DateTranslation.DateTranslationLocalDateTime(taskIdEndTime),
                        DateTranslation.DateTranslationLocalDateTime(taskTempleBeginTime),
                        DateTranslation.DateTranslationLocalDateTime(taskTempleEndTime));

                if (isOver.equals(Boolean.TRUE))
                    return Result.failure(ResultCode.TASK_ERROR_REPEAT_TIME);

            }

        }
        //参加活动了
        Boolean temple=linkTaskService.participateTask(user.getId(),taskId);

        if(temple.equals(Boolean.TRUE)){

            int people = task.getPeople();

            people=people+1;

            task.setPeople(people);
            //表更新（任务表更新状态，支付表更新状态）

            save(task);

            return Result.success(ResultCode.TASK_SUCCESS_TAKE_PART_IN);
        }else {

            return Result.failure(ResultCode.INTERNAL_ERROR);
        }

    }

    @Override
    public Result deleteTask(String taskId) {

        Task task = taskMapper.selectById(taskId);

        Wxuser user = AccountHolder.getUser();
        //状态只有3或5可以删除
        if(user.getId().equals(task.getInitiator())&&(task.getStatus().equals(3)||task.getStatus().equals(5))){

            taskMapper.deleteById(taskId);

            return Result.success(ResultCode.TASK_SUCCESS_DELETE);
        }else {

            return Result.failure(ResultCode.TASK_ERROR_DELETE);
        }
    }

    /**
     * 查看我的接单
     */
    @Override
    public Result myOrder(PageRequest pageRequest) {
        Wxuser user = AccountHolder.getUser();

        IPage<Task> myAllOrder = linkTaskMapper.getMyAllOrder(user.getId(), new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize()));

        Page<Task> taskPage = new Page<>();
        BeanUtils.copyProperties(myAllOrder,taskPage);

        return getResult(taskPage);
    }

    /**
     * 申请退款
     */
    @Override
    public Result refund(String taskId) {
        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();
        taskQueryWrapper.eq("id",taskId).eq("status",0).eq("initiator",taskId);
        Task task = getOne(taskQueryWrapper);

        if (task==null){
            return Result.success("删除失败，请查看你的任务id是否正确或者你的任务是否已经被接单");
        }

        QueryWrapper<Payment> paymentQueryWrapper = new QueryWrapper<>();
        paymentQueryWrapper.eq("product_id",task.getId()).eq("status_code","SUCCESS").eq("payer",user.getId());

        Payment payment = paymentMapper.selectOne(paymentQueryWrapper);

        if(payment==null){
            return Result.success("请查看你是否已经支付成功!");
        }
        Boolean refund = payOwn.refund(new CreateRefundDTO(payment.getOutTradeNo(), task.getPrice(), task.getPrice()));


        task.setStatus(6);
        taskMapper.updateById(task);

        linkTaskService.deleteParticipate(taskId);

        return Result.success(refund);
    }

    @Override
    public Result singOUt(TaskSignOutRequest taskSignOutRequest) {

        return null;//缺少打款
    }
}




