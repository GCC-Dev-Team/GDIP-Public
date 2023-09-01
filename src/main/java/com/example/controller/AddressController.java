package com.example.controller;

import com.example.common.Result;
import com.example.model.dto.AddAddressRequest;
import com.example.model.dto.UpdateAddressRequest;
import com.example.service.AddressService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Resource
    AddressService addressService;
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

    @PutMapping
    public Result updateMyAddress(@NotNull @RequestBody UpdateAddressRequest updateAddressId){
        return addressService.updateMyAddress(updateAddressId);
    }

    /**
     * 根据地址id地址详细信息
     * @param addressId
     * @return
     */
    @GetMapping
    public String addressDescribeById(@NotNull String addressId){

        return addressService.getAllAddressDescribeById(addressId);
    }

    /**
     * 获取我的某个地址的详细信息
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
    @DeleteMapping
    public Result deleteAddress(@NotNull String addressId){
        return addressService.deleteAddress(addressId);
    }


}
