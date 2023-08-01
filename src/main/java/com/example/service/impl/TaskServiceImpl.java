package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.model.dto.GetTaskIdRequest;
import com.example.model.dto.ParticipateTaskRequest;
import com.example.model.dto.TaskCreateRequest;
import com.example.model.entity.Task;
import com.example.model.entity.Wxuser;
import com.example.model.vo.TaskSmallVO;
import com.example.model.vo.TaskVO;
import com.example.service.TaskService;
import com.example.mapper.TaskMapper;
import com.example.utils.AccountHolder;
import com.example.utils.DateUtils;
import com.example.utils.ShowPhotoUtil;
import com.example.utils.UploadPhotoUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    @Override
    public Result createTask(TaskCreateRequest taskCreateRequest) {

        Wxuser user = AccountHolder.getUser();

        Date dateStart = DateUtils.stringToDate(taskCreateRequest.getStartTime());

        Date dateEnd=DateUtils.stringToDate(taskCreateRequest.getEndTime());

        String taskId=UUID.randomUUID().toString();

        String singOut=getCode();


        Task task = new Task();

        task.setId(taskId);
        task.setActivityTitle(taskCreateRequest.getActivityTitle());
        task.setLocation(taskCreateRequest.getLocation());
        task.setInitiator(user.getId());
        task.setIsCompleted(0);
        task.setNumberOfParticipants(taskCreateRequest.getNumberOfParticipants());
        task.setActivityDescription(taskCreateRequest.getActivityDescription());
        task.setStartTime(dateStart);
        task.setEndTime(dateEnd);
        task.setSignOutCode(singOut);

        this.save(task);

        return Result.success();
    }

    @Override
    public Result getTaskById(GetTaskIdRequest getTaskIdRequest) {

        String id=getTaskIdRequest.getId();

        if(id.isEmpty()){
            return Result.failure(ResultCode.PARAM_IS_BLANK);
        }

        Wxuser user = AccountHolder.getUser();

        Task task = getById(id);

        TaskVO taskVO = new TaskVO();


        taskVO.setId(task.getId());
        taskVO.setInitiator(task.getInitiator());
        taskVO.setLocation(task.getLocation());
        taskVO.setEndTime(task.getEndTime());
        taskVO.setStartTime(task.getStartTime());
        taskVO.setActivityTitle(task.getActivityTitle());
        taskVO.setActivityDescription(task.getActivityDescription());
        taskVO.setImageUrl(task.getImageUrl());
        taskVO.setNumberOfParticipants(task.getNumberOfParticipants());



        if(task.getInitiator().equals(user.getId())){
            //这个是自己发布查看的任务详情
            taskVO.setSignOutCode(task.getSignOutCode());
        }

        taskVO.setIsCompleted(task.getIsCompleted());
        return Result.success(taskVO);
    }

    /**
     * 获取我发布的任务
     * @return
     */
    @Override
    public Result getTaskSmall() {

        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();

        taskQueryWrapper.eq("initiator",user.getId());

        return getResult(taskQueryWrapper);
    }

    @Override
    public Result getAllTask() {

        QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();

        taskQueryWrapper.ge("start_time",new Date());

        return getResult(taskQueryWrapper);
    }

    @NotNull
    private Result getResult(QueryWrapper<Task> taskQueryWrapper) {
        List<Task> tasks = taskMapper.selectList(taskQueryWrapper);

        List<TaskSmallVO> taskSmallVOList =new ArrayList<>();

        if(!tasks.isEmpty()){

            for (Task taskTemple : tasks) {
                TaskSmallVO taskSmallVo = new TaskSmallVO();

                taskSmallVo.setTitle(taskTemple.getActivityTitle());
                taskSmallVo.setId(taskTemple.getId());
                taskSmallVo.setBeginTime(taskTemple.getStartTime());
                taskSmallVo.setEndTime(taskTemple.getEndTime());
                taskSmallVo.setUrl(taskTemple.getImageUrl());

                taskSmallVOList.add(taskSmallVo);
            }

        }

        return Result.success(taskSmallVOList);
    }

    @Override
    public Result uploadTaskPhoto(MultipartFile file, String id) {

        String name="Task:"+id;

        UploadPhotoUtil.uploadFile(file,name);

        String photoByName = ShowPhotoUtil.getPhotoByName(name);

        Task task = this.getById(id);

        if( task.getImageUrl()==null){

            task.setImageUrl(photoByName);

        }else {

           String oldUrl=task.getImageUrl();

           String newUrl=oldUrl+","+photoByName;

           task.setImageUrl(newUrl);
        }

        this.updateById(task);

        return Result.success();
    }

    @Override
    public Result participateTask(ParticipateTaskRequest participateTaskRequest) {
        return null;
    }

    public static String getCode() {
        Random random= new Random();
        int randomCode =random.nextInt(100000,999999);
        return randomCode+"";
    }
}




