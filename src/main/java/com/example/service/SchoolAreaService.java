package com.example.service;

import com.example.common.Result;
import com.example.model.entity.SchoolArea;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.validation.constraints.NotNull;

/**
* @author L
* @description 针对表【school_area】的数据库操作Service
* @createDate 2023-09-01 11:48:55
*/
public interface SchoolAreaService extends IService<SchoolArea> {

    /**
     * 获取全部学校
     * @return
     */
    Result getAllSchool();

    /**
     * 获取校区
     * @param schoolId
     * @return
     */

    Result getAllCampus(@NotNull String schoolId);

    /**
     * 获取学校区域
     * @param school
     * @param campus
     * @return
     */
    Result getAllArea(@NotNull String school,@NotNull String campus);

    /**
     *获取学校代码
     */
    String getCode(@NotNull String school,@NotNull String campus,@NotNull String area);

    /**
     * code获取school（返回schoolVO）
     * @param code
     * @return
     */
    Result getSchoolName(@NotNull String code);

    /**
     * 根据code获取校区
     * @param code
     * @return
     */
    Result getCampusName(@NotNull String code);

    /**
     * 根据code获取校园位置
     * @param code
     * @return
     */
    Result getAreaName(@NotNull String code);
}
