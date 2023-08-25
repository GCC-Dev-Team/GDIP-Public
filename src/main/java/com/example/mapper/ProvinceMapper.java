package com.example.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.model.entity.Province;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.vo.AreaVO;
import com.example.model.vo.CityVO;
import com.example.model.vo.ProvinceVo;
import com.example.model.vo.TownVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author L
* @description 针对表【province】的数据库操作Mapper
* @createDate 2023-08-09 00:55:24
* @Entity com.example.model.entity.Province
*/
public interface ProvinceMapper extends BaseMapper<Province> {

    /**
     * 省级
     * @return
     */
    @Select("SELECT province.province,province.`name` FROM `province` WHERE city=0 AND area=0 AND town=0")
    List<ProvinceVo> getAllProvinces();

    /**
     * 市级
     * @param provinceId
     * @return
     */
    @Select("SELECT province.city,province.`name` FROM `province` WHERE province=#{provinceId} AND area=0 AND town=0")
    List<CityVO> getAllCity(@Param("provinceId") String provinceId);

    /**
     * 区级
     * @param provinceId
     * @param cityId
     * @return
     */
    @Select("SELECT province.area,province.`name` FROM `province` WHERE province=#{provinceId} AND city=#{cityId}  AND town=0")
    List<AreaVO> getAllArea(@Param("provinceId")String provinceId,@Param("cityId")String cityId);

    /**
     * 获取镇级/街道
     * @param provinceId
     * @param cityId
     * @param areaId
     * @return
     */
    @Select("SELECT province.town,province.`name` FROM `province` WHERE province=#{provinceId} AND city=#{cityId}  AND area =#{areaId}")
    List<TownVO> getAllTown(@Param("provinceId") String provinceId,@Param("cityId")String cityId,@Param("areaId")String areaId);

    /**
     * 获取id
     * @param provinceId
     * @param cityId
     * @param areaId
     * @param townId
     * @return
     */
    @Select("SELECT id FROM `province` WHERE province=#{provinceId} AND city=#{cityId}  AND area =#{areaId} AND town=#{townId}")
    int getAddressCode(@Param("provinceId") String provinceId,@Param("cityId")String cityId,@Param("areaId")String areaId,@Param("townId")String townId);

    /**
     * 获取省名
     * @param provinceId
     * @return
     */
    @Select("SELECT province.`name` FROM `province` WHERE city=0 AND area=0 AND town=0 AND province=#{provinceId}")
    String getProvinceNameById(@Param("provinceId")String provinceId);

    /**
     * 获取市名字
     * @param provinceId
     * @param cityId
     * @return
     */
    @Select("SELECT province.`name`FROM `province` WHERE province=#{provinceId} AND area=0 AND town=0 AND city=#{cityId}")
    String getCityNameById(@Param("provinceId")String provinceId,@Param("cityId")String cityId);

    /**
     * 获取区
     * @param provinceId
     * @param cityId
     * @param areaId
     * @return
     */
    @Select("SELECT province.`name`FROM `province` WHERE province=#{provinceId} AND area=#{areaId} AND town=0 AND city=#{cityId}")
    String getAreaNameById(@Param("provinceId")String provinceId,@Param("cityId")String cityId,@Param("areaId")String areaId);

    /**
     * 获取街道
     * @param provinceId
     * @param cityId
     * @param areaId
     * @param townId
     * @return
     */
    @Select("SELECT province.`name`FROM province WHERE province=#{provinceId} AND area=#{areaId} AND town=#{townId} AND city=#{cityId}")
    String getTownNameById(@Param("provinceId")String provinceId,@Param("cityId")String cityId,@Param("areaId")String areaId,@Param("townId")String townId);

}




