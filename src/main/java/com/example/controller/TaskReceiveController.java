package com.example.controller;

import com.example.common.Result;
import com.example.model.dto.PageRequest;
import com.example.model.dto.ParticipateTaskRequest;
import com.example.model.dto.TaskSignOutRequest;
import com.example.service.TaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/taskReceive")
public class TaskReceiveController {
    @Resource
    TaskService taskService;
    /**
     * 查看我的接单（接单视角）（小图）
     */
    @PostMapping("/myOrder")
    public Result myOrder(@NotNull@RequestBody PageRequest pageRequest){
        return taskService.myOrder(pageRequest);
    }

    /**
     *  取消接单（接单视角），一天限制3次（如果超过了，会扣除信用分）（信用程度）
     */



    /**
     * 签退
     */
    @PostMapping("/signOut")
    public Result singOUt(@NotNull@RequestBody TaskSignOutRequest taskSignOutRequest){
        return taskService.singOUt(taskSignOutRequest);
    }


    /**
     * 报名参加任务
     */
    @PostMapping("/participateTask")
    public Result participateTask(@NotNull ParticipateTaskRequest participateTaskRequest){

        return taskService.participateTask(participateTaskRequest);
    }
}
