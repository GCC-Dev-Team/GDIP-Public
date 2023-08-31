package com.example.controller;

import com.example.common.Result;
import com.example.model.dto.AddAddressRequest;
import com.example.model.dto.UpdateAddressRequest;
import com.example.service.AddressService;
import com.example.service.ProvinceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Resource
    AddressService addressService;

    @Resource
    ProvinceService provinceService;
    /**
     * 获取省份以及省份编号
     * @return
     */
    @GetMapping("/province")
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

    /**
     * 新增地址
     * @param addAddressRequest
     * @return
     */
    @PostMapping
    public Result addAddress(@NotNull @RequestBody AddAddressRequest addAddressRequest){
        return addressService.addAddress(addAddressRequest);
    }

    /**
     * 获取我所有的地址
     * @return
     */

    @GetMapping("/myAddress")
    public Result getMyAllAddress(){
        return addressService.getMyAllAddress();
    }

    /**
     * 修改我的地址
     * @param
     * @return
     */

    @PutMapping("/myAddress")
    public Result updateMyAddress(@NotNull @RequestBody UpdateAddressRequest updateAddressId){
        return addressService.updateMyAddress(updateAddressId);
    }

    /**
     * 根据地址id获取所有的地址详细的
     * @param addressId
     * @return
     */
    @GetMapping("/addressDescribeById")
    public String addressDescribeById(@NotNull String addressId){

        return addressService.getAllAddressDescribeById(addressId);
    }

    /**
     * 获取某个地址的详细信息
     * @param addressId
     * @return
     */
    @GetMapping("/myAddressDescribe")
    public Result getmyAddressDescribe(@NotNull String addressId){

        return addressService.getmyAddressDescribe(addressId);
    }

    /**
     * 删除地址
     * @param addressId
     * @return
     */
    @DeleteMapping("/myAddress")
    public Result deleteAddress(@NotNull String addressId){
        return addressService.deleteAddress(addressId);
    }


}
