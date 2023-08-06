package com.example.controller;

import com.example.common.Result;
import com.example.model.dto.GetTaskIdRequest;
import com.example.model.dto.ParticipateTaskRequest;
import com.example.model.dto.TaskCreateRequest;
import com.example.service.TaskService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Resource
    TaskService taskService;

    /**
     * 发布任务
     * @param taskCreateRequest
     * @return
     */
    @PostMapping
    public Result createTask(@RequestBody TaskCreateRequest taskCreateRequest){

        return taskService.createTask(taskCreateRequest);
    }

    /**
     * 获取任务的详细信息
     * @param getTaskIdRequest
     * @return
     */
    @GetMapping
    public Result getTaskById(@RequestBody GetTaskIdRequest getTaskIdRequest){

       return taskService.getTaskById(getTaskIdRequest);
    }
    /**
     * 查看我发布的任务（小图）
     */
    @GetMapping("/myTask")
    public Result getTaskSmall(){

        return taskService.getTaskSmall();

    }
    /**
     * 查看所有的活动
     */
    @GetMapping("/allTask")
    public Result getAllTask(){

        return taskService.getAllTask();
    }

    /**
     * 上传任务图片的接口
     * @param file 文件
     * @param id 任务id
     * @return
     */
    @PostMapping("/uploadTaskPhoto")
    public Result uploadTaskPhoto(@NotNull MultipartFile[] file, @NotNull String id){

        return taskService.uploadTaskPhoto(file,id);

    }
    /**
     * 报名参加任务
     */
    @PostMapping("/participateTask")
    public Result participateTask(@NotNull ParticipateTaskRequest participateTaskRequest){

        return taskService.participateTask(participateTaskRequest);

    }

    /**
     * 删除活动，根据taskid
     * @param taskId
     * @return
     */
    @DeleteMapping
    public  Result deleteTask(@NotNull String taskId){

        return taskService.deleteTask(taskId);

    }


}
