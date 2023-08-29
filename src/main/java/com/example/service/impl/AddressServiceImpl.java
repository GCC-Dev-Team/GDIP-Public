package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.model.dto.AddAddressRequest;
import com.example.model.dto.UpdateAddressRequest;
import com.example.model.entity.Address;
import com.example.model.entity.Wxuser;
import com.example.model.vo.AddressVO;
import com.example.service.AddressService;
import com.example.mapper.AddressMapper;
import com.example.utils.AccountHolder;
import com.example.utils.AddressUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author L
 * @description 针对表【address】的数据库操作Service实现
 * @createDate 2023-08-09 00:55:43
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
        implements AddressService {
    @Resource
    AddressMapper addressMapper;
    @Resource
    AddressUtil addressUtil;

    @Override
    public Result addAddress(AddAddressRequest addAddressRequest) {
        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();

        QueryWrapper<Address> eq = addressQueryWrapper.eq("creator", user.getId()).eq("region_id", addAddressRequest.getAddressCode()).eq("detailed_address", addAddressRequest.getDescribe());

        Address one = getOne(eq);

        if (one != null) {
            return Result.failure(ResultCode.ADDRESS_REPEAT);
        }

        Address address = new Address();

        address.setId("address:" + UUID.randomUUID());
        address.setDetailedAddress(addAddressRequest.getDescribe());
        address.setCreator(user.getId());
        address.setRegionId(String.valueOf(addAddressRequest.getAddressCode()));

        save(address);

        return Result.success();
    }


    @Override
    public Result getMyAllAddress() {
        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();

        addressQueryWrapper.eq("creator", user.getId());

        List<Address> addresses = addressMapper.selectList(addressQueryWrapper);

        List<AddressVO> addressVOS = new ArrayList<>();

        for (Address address : addresses) {
            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(address, addressVO);
            //加上省市区

            String regionName = addressUtil.getRegionName(addressVO.getRegionId());
            addressVO.setRegion(regionName);

            addressVOS.add(addressVO);
        }

        return Result.success(addressVOS);
    }

    @Override
    public Result updateMyAddress(UpdateAddressRequest updateAddressId) {
       int temple=0;

        String addressId = updateAddressId.getAddressId();

        Address byId = getById(addressId);

        if (updateAddressId.getAddressCode()!=null){

            byId.setRegionId(String.valueOf(updateAddressId.getAddressCode()));

            temple=temple+1;
        }

        if(updateAddressId.getDescribe()!=null){

            byId.setDetailedAddress(updateAddressId.getDescribe());

            temple=temple+1;
        }

        if(temple>0){

            save(byId);
        }

        return Result.success();
    }

    @Override
    public Result deleteAddress(String addressId) {

        Address byId = getById(addressId);

        Wxuser user = AccountHolder.getUser();

        boolean equals = byId.getCreator().equals(user.getId());

        if(equals){

            addressMapper.deleteById(byId);

            return Result.success();
        }
        return Result.failure(ResultCode.ADDRESS_DELETE_ERROR);
    }

    @Override
    public Result getmyAddressDescribe(String addressId) {
        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();

        addressQueryWrapper.eq("id",addressId);

        Address address = addressMapper.selectOne(addressQueryWrapper);
        if(address==null){
            log.error("查询地址失败!");
            throw new RuntimeException("请核实地址id是否正确，是否已经登录!");
        }
        //这里可以优化

        AddressVO addressVO = new AddressVO();
        addressVO.setId(address.getId());
        addressVO.setRegionId(address.getRegionId());
        addressVO.setRegion(addressUtil.getRegionName(address.getRegionId()));
        addressVO.setDetailedAddress(address.getDetailedAddress());

        return Result.success(addressVO);

    }

    @Override
    public String getAllAddressDescribeById(String addressId) {

        Address address = addressMapper.selectById(addressId);

       return addressUtil.getRegionName(address.getRegionId())+address.getDetailedAddress();

    }
}




