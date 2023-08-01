package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.model.entity.LinkTask;
import com.example.service.LinkTaskService;
import com.example.mapper.LinkTaskMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
* @author L
* @description 针对表【link_task】的数据库操作Service实现
* @createDate 2023-08-01 10:21:07
*/
@Service
public class LinkTaskServiceImpl extends ServiceImpl<LinkTaskMapper, LinkTask>
    implements LinkTaskService{
    @Resource
    LinkTaskMapper linkTaskMapper;
    @Override
    public List<String> getMyNoSingOutTask(@NotNull String userId) {


        return linkTaskMapper.getMyNoSingOutList(userId);
    }

    @Override
    public Boolean participateTask(String userId, String taskId) {

        LinkTask linkTask = new LinkTask();

        linkTask.setId("taskLink:"+UUID.randomUUID().toString());
        linkTask.setTaskId(taskId);
        linkTask.setParticipantId(userId);
        linkTask.setIsSignedOut(0);

        this.save(linkTask);

        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteParticipate(String taskId) {

        QueryWrapper<LinkTask> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("task_id",taskId);

//        List<LinkTask> list = linkTaskMapper.selectList(queryWrapper);

        linkTaskMapper.delete(queryWrapper);
      return Boolean.TRUE;
    }
}




