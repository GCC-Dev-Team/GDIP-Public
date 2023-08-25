package com.example.utils;

import com.example.mapper.AddressMapper;
import com.example.mapper.ProvinceMapper;
import com.example.model.entity.Province;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
public class AddressUtil {
    @Resource
    AddressMapper addressMapper;
    @Resource
    ProvinceMapper provinceMapper;

    /**
     * 根据id回掉查询区域
     * @param regionId
     * @return
     */
    public String getRegionName(String regionId){

        int intValue = Integer.parseInt(regionId);

        Province province = provinceMapper.selectById(intValue);

        String provinceId = province.getProvince();
        String cityId = province.getCity();
        String areaId= province.getArea();
        String townId=province.getTown();

        return provinceMapper.getProvinceNameById(provinceId)+provinceMapper.getCityNameById(provinceId,cityId)
                +provinceMapper.getAreaNameById(provinceId,cityId,areaId)+provinceMapper.getTownNameById(provinceId,cityId,areaId,townId);
    }
}
