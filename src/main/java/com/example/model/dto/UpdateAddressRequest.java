package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAddressRequest {
    /**
     * 地址id
     */
    private String addressId;
    /**
     * 地址的省市区镇代码（从拿代码接口拿）
     */
    private Integer addressCode;

    /**
     * 详细地址描述
     */
    private String describe;
}
