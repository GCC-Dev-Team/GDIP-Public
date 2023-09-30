package com.example.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.model.entity.Province;
import com.example.service.ProvinceService;
import com.example.mapper.ProvinceMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author L
* @description 针对表【province】的数据库操作Service实现
* @createDate 2023-08-09 00:55:24
*/
@Service
@Transactional
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province>
    implements ProvinceService{
    @Resource
    ProvinceMapper provinceMapper;
    @Override
    public Result getAllProVince() {

        return Result.success(provinceMapper.getAllProvinces());
    }

    @Override
    public Result getAllCity(String provinceId) {
        return Result.success(provinceMapper.getAllCity(provinceId));
    }

    @Override
    public Result getAllArea(String provinceId, String cityId) {
        return Result.success(provinceMapper.getAllArea(provinceId,cityId));
    }

    @Override
    public Result getAllTown(String provinceId, String cityId, String areaId) {
        return Result.success(provinceMapper.getAllTown(provinceId,cityId,areaId));
    }

    @Override
    public Result getAddressCode(String provinceId, String cityId, String areaId, String townId) {
        return Result.success(provinceMapper.getAddressCode(provinceId,cityId,areaId,townId));
    }
}




