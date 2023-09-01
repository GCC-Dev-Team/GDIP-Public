package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.model.entity.SchoolArea;
import com.example.service.SchoolAreaService;
import com.example.mapper.SchoolAreaMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author L
* @description 针对表【school_area】的数据库操作Service实现
* @createDate 2023-09-01 11:48:55
*/
@Service
public class SchoolAreaServiceImpl extends ServiceImpl<SchoolAreaMapper, SchoolArea>
    implements SchoolAreaService{
    @Resource
    SchoolAreaMapper schoolAreaMapper;
    @Override
    public Result getAllSchool() {
        return Result.success(schoolAreaMapper.getAllSchool());
    }

    @Override
    public Result getAllCampus(String schoolId) {
        return Result.success(schoolAreaMapper.getAllCampusBySchool(schoolId));
    }

    @Override
    public Result getAllArea(String school, String campus) {
        return Result.success(schoolAreaMapper.getAreaById(school,campus));
    }

    @Override
    public String getCode(String school, String campus, String area) {

        return schoolAreaMapper.getSchoolCode(school,campus,area);
    }
}




