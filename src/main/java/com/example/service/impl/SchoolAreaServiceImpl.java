package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.model.entity.SchoolArea;
import com.example.model.vo.AreaVO;
import com.example.model.vo.CampusVO;
import com.example.model.vo.SchoolVO;
import com.example.service.SchoolAreaService;
import com.example.mapper.SchoolAreaMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author L
* @description 针对表【school_area】的数据库操作Service实现
* @createDate 2023-09-01 11:48:55
*/
@Service
@Transactional
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

    @Override
    public Result getSchoolName(String code) {

        QueryWrapper<SchoolArea> id = new QueryWrapper<SchoolArea>().eq("code", code);
        SchoolArea schoolArea = schoolAreaMapper.selectOne(id);

        String school = schoolArea.getSchool();
        String schoolName = schoolAreaMapper.getSchoolName(school);


        return Result.success(new SchoolVO(schoolName,school));
    }

    @Override
    public Result getCampusName(String code) {


        QueryWrapper<SchoolArea> id = new QueryWrapper<SchoolArea>().eq("code", code);
        SchoolArea schoolArea = schoolAreaMapper.selectOne(id);

        String campus = schoolArea.getCampus();
        String school = schoolArea.getSchool();
        String campusName = schoolAreaMapper.getCampusName(school, campus);


        return Result.success(new CampusVO(campus,campusName));
    }

    @Override
    public Result getAreaName(String code) {
        QueryWrapper<SchoolArea> id = new QueryWrapper<SchoolArea>().eq("code", code);
        SchoolArea schoolArea = schoolAreaMapper.selectOne(id);

        String campus = schoolArea.getCampus();
        String school = schoolArea.getSchool();

        String area = schoolArea.getArea();

        String areaName = schoolAreaMapper.getAreaName(school, campus, area);

        return Result.success(new AreaVO(area,areaName));
    }
}




