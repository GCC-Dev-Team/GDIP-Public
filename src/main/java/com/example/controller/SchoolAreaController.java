package com.example.controller;

import com.example.common.Result;
import com.example.service.SchoolAreaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/schoolAddress")
public class SchoolAreaController {

    @Resource
    SchoolAreaService schoolAreaService;

    /**获取所有学校
     * @return
     */
    @GetMapping
    public Result getAllSchool(){

        return schoolAreaService.getAllSchool();
    }

    /**
     * 获取所有校区
     * @param schoolId
     * @return
     */
    @GetMapping("/campus")
    public Result getAllCampus(@NotNull String schoolId){

        return schoolAreaService.getAllCampus(schoolId);
    }

    /**
     * 获取学校区域
     * @param school
     * @param campus
     * @return
     */
    @GetMapping("/area")
    public Result getAllArea(@NotNull String school,@NotNull String campus){

        return schoolAreaService.getAllArea(school,campus);
    }

    /**
     * 获取code
     * @param school
     * @param campus
     * @param area
     * @return
     */
    @GetMapping("/code")
    public String getCode(@NotNull String school,@NotNull String campus,@NotNull String area){
        return schoolAreaService.getCode(school,campus,area);
    }

}
