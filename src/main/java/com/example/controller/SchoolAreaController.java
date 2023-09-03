package com.example.controller;

import com.example.common.Result;
import com.example.service.SchoolAreaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 学校地址获取（获取编码也是）
 */
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

    /**
     * 给我code获取学校
     * @param code
     * @return
     */

    @GetMapping("/nameSchool")
    public Result getSchoolName(@NotNull String code){
        return schoolAreaService.getSchoolName(code);
    }

    /**
     * 给我code获取校区
     * @param code
     * @return
     */
    @GetMapping("/nameCampus")
    public Result getCampusName(@NotNull String code){
       return schoolAreaService.getCampusName(code);
    }

    /**
     * 给我code获取校园位置
     * @param code
     * @return
     */
    @GetMapping("/nameArea")
    public Result getAreaName(@NotNull String code){

        return schoolAreaService.getAreaName(code);
    }

}
