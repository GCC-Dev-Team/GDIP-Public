package com.example.service;

import com.example.model.entity.LinkTask;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author L
* @description 针对表【link_task】的数据库操作Service
* @createDate 2023-08-01 10:21:07
*/
public interface LinkTaskService extends IService<LinkTask> {

    /**
     * 获取我没有签退的任务
     * @param userId
     * @return
     */

    List<String> getMyNoSingOutTask(String userId);

    /**
     * 报名参加活动
     * @param userId
     * @param taskId
     * @return
     */
    Boolean participateTask(String userId,String taskId);

    /**
     * 删除活动联系(接单者取消接单)
     */
    void deleteParticipate(String taskId);
}
