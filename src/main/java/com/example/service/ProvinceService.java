package com.example.service;

import com.example.common.Result;
import com.example.model.entity.Province;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.validation.constraints.NotNull;

/**
* @author L
* @description 针对表【province】的数据库操作Service
* @createDate 2023-08-09 00:55:24
*/
public interface ProvinceService extends IService<Province> {
    Result getAllProVince();

    Result getAllCity(@NotNull String provinceId);

    Result getAllArea(@NotNull String provinceId, @NotNull String cityId);

    Result getAllTown(@NotNull String provinceId, @NotNull String cityId, @NotNull String areaId);

    Result getAddressCode(@NotNull String provinceId, @NotNull String cityId, @NotNull String areaId, @NotNull String townId);

}