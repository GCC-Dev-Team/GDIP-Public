package com.example.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.model.entity.LinkTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.entity.Task;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author L
* @description 针对表【link_task】的数据库操作Mapper
* @createDate 2023-08-01 10:21:07
* @Entity com.example.model.entity.LinkTask
*/
public interface LinkTaskMapper extends BaseMapper<LinkTask> {

    /**
     * 查看我已经报名没有结束的任务id
     * @param userId
     * @return
     */
    @Select("SELECT link_task.id FROM link_task WHERE participant_id=#{userId} AND is_signed_out=0;")
    List<String> getMyNoSingOutList (@Param("userId") String userId);

    /**
     * 查看我的接单（所有的）
     */
    @Select("SELECT task.* FROM link_task LEFT JOIN task ON task.id=link_task.task_id WHERE link_task.participant_id=#{userId}")
    IPage<Task> getMyAllOrder(@Param("userId") String userId, Page<Task> page);
}




