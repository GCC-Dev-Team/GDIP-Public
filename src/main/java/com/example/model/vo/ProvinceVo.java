package com.example.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceVo {


    /**
     * 省份编号
     */
    private String province;

    /**
     * 省份名称
     */
    private String name;
}
