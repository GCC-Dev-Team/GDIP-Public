package com.example.controller;

import com.example.common.Result;
import com.example.service.ProvinceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
@RestController
@RequestMapping("/province")
public class ProvinceController {
    @Resource
    ProvinceService provinceService;
    /**
     * 获取省份以及省份编号
     * @return
     */
    @GetMapping
    public Result getAllProVince(){

        return provinceService.getAllProVince();
    }

    /**
     * 获取城市
     * @param provinceId 省id
     * @return
     */
    @GetMapping("/city")
    public Result getAllCity(@NotNull String provinceId){

        return provinceService.getAllCity(provinceId);
    }

    /**
     * 获取区
     * @param provinceId 省id
     * @param cityId 市id
     * @return
     */
    @GetMapping("/area")
    public Result getAllArea(@NotNull String provinceId, @NotNull String cityId){

        return provinceService.getAllArea(provinceId,cityId);
    }

    /**
     * 获取镇/街道
     * @param provinceId 省id
     * @param cityId 市id
     * @param areaId 区id
     * @return
     */
    @GetMapping("/town")
    public Result getAllTown(@NotNull String provinceId,@NotNull String cityId, @NotNull String areaId){

        return provinceService.getAllTown(provinceId,cityId,areaId);
    }

    /**
     * 获取code（代表省市区镇）
     * @param provinceId 省id
     * @param cityId 市id
     * @param areaId 区id
     * @param townId 镇id
     * @return int （代表省市区镇）
     */

    @GetMapping("/Code")
    public Result getAddressCode(@NotNull String provinceId, @NotNull String cityId,@NotNull String areaId,@NotNull String townId){
        return provinceService.getAddressCode(provinceId,cityId,areaId,townId);
    }

}
