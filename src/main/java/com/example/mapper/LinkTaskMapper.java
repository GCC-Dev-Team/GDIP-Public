package com.example.mapper;

import com.example.model.entity.LinkTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

    @Select("SELECT link_task.id FROM link_task WHERE participant_id=#{userId} AND is_signed_out=0;")
    List<String> getMyNoSingOutList (@Param("userId") String userId);
}




