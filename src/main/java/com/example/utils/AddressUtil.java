package com.example.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.mapper.ProvinceMapper;
import com.example.mapper.SchoolAreaMapper;
import com.example.model.entity.Province;
import com.example.model.entity.SchoolArea;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
public class AddressUtil {
    @Resource
    SchoolAreaMapper schoolAreaMapper;
    @Resource
    ProvinceMapper provinceMapper;

    /**
     * 根据id回掉查询区域
     * @param regionId 前端给来的id或者给到的code（学校表）
     * @return
     */
    public String getRegionName(String regionId){

        int length = regionId.length();

        if(length!=8){

            int intValue = Integer.parseInt(regionId);

            Province province = provinceMapper.selectById(intValue);

            String provinceId = province.getProvince();
            String cityId = province.getCity();
            String areaId= province.getArea();
            String townId=province.getTown();

            return provinceMapper.getProvinceNameById(provinceId)+provinceMapper.getCityNameById(provinceId,cityId)
                    +provinceMapper.getAreaNameById(provinceId,cityId,areaId)+provinceMapper.getTownNameById(provinceId,cityId,areaId,townId);
        }

        //自己学校给的是code，省市区那些给的是id
        QueryWrapper<SchoolArea> schoolAreaQueryWrapper = new QueryWrapper<>();

        schoolAreaQueryWrapper.eq("code",regionId);

        SchoolArea schoolArea = schoolAreaMapper.selectOne(schoolAreaQueryWrapper);

        String school = schoolArea.getSchool();
        String campus = schoolArea.getCampus();
        String area = schoolArea.getArea();

        return  schoolAreaMapper.getSchoolName(school)+
                schoolAreaMapper.getCampusName(school,campus)+
                schoolAreaMapper.getAreaName(school,campus,area);

    }
}
