package com.example.service;

import com.example.common.Result;
import com.example.model.dto.GetTaskIdRequest;
import com.example.model.dto.PageRequest;
import com.example.model.dto.ParticipateTaskRequest;
import com.example.model.dto.TaskCreateRequest;
import com.example.model.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
* @author L
* @description 针对表【task】的数据库操作Service
* @createDate 2023-07-31 10:56:35
*/
public interface TaskService extends IService<Task> {

    /**
     * 创建任务
     * @param taskCreateRequest
     * @return
     */
    Result createTask(@RequestBody TaskCreateRequest taskCreateRequest);

    /**
     * 根据任务id查看全部信息
     * @param getTaskIdRequest
     * @return
     */
    Result getTaskById(@RequestBody GetTaskIdRequest getTaskIdRequest);

    /**
     * 查看我发布的任务（小信息/图片）
     * @return
     */
    Result getTaskSmall(@NotNull @RequestBody PageRequest pageRequest);

    /**
     * 任务大厅展示的任务
     * @return
     */
    Result getAllTask(@NotNull @RequestBody PageRequest pageRequest);

    /**
     * 图片上传
     */
    Result uploadTaskPhoto(@NotNull MultipartFile file, @NotNull String id);

    /**
     * 参加活动
     */
    Result participateTask(@NotNull ParticipateTaskRequest participateTaskRequest);

    /**
     * 删除活动
     */
    Result deleteTask(String taskId);
}
