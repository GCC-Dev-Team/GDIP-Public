package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAddressRequest {
    /**
     * 地址的省市区镇代码（从拿代码接口拿）(也是学校的那个一样的但是不是int)
     */
    private Integer addressCode;

    /**
     * 详细地址描述
     */
    private String describe;
}
