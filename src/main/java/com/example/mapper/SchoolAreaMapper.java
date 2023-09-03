package com.example.mapper;

import com.example.model.entity.SchoolArea;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.vo.AreaVO;
import com.example.model.vo.CampusVO;
import com.example.model.vo.SchoolVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author L
* @description 针对表【school_area】的数据库操作Mapper
* @createDate 2023-09-01 11:48:55
* @Entity com.example.model.entity.SchoolArea
*/
public interface SchoolAreaMapper extends BaseMapper<SchoolArea> {
    /**
    * 获取学校
     */
    @Select("SELECT `name`,school FROM school_area WHERE campus=0 AND area=0;")
    List<SchoolVO> getAllSchool();

    /**
     * 获取校区
     */
    @Select("SELECT `name`,campus FROM school_area WHERE school=#{school} AND area=0;")
    List<CampusVO> getAllCampusBySchool(@Param("school")String school);


    /**
     * 获取区域
     */
    @Select("SELECT `name`,area FROM school_area WHERE school=#{school} AND campus=#{campus};")
    List<AreaVO> getAreaById(@Param("school")String school,@Param("campus")String campus);

    /**
     * 获取code
     */
    @Select("SELECT `code` FROM school_area WHERE school=#{school} AND campus=#{campus} AND area=#{area};")
    String getSchoolCode(@Param("school")String school,@Param("campus")String campus,@Param("area")String area);


    /**
     * 获取学校名字（根据code）
     */
    @Select("SELECT `name` FROM school_area WHERE school=#{school} AND campus=0 AND area=0;")
    String getSchoolName(@Param("school")String school);
    /**
     * 获取校区
     */
    @Select("SELECT `name` FROM school_area WHERE school=#{school} AND campus=#{campus} AND area=0;")
    String getCampusName(@Param("school")String school,@Param("campus")String campus);

    /**
     * 获取区域名字
     */

    @Select("SELECT `name` FROM school_area WHERE school=#{school} AND campus=#{campus} AND area=#{area};")
    String getAreaName(@Param("school")String school,@Param("campus")String campus,@Param("area")String area);
}




