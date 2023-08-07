package com.example.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVO {

    /**
     * 类别id
     */
    private String id;

    /**
     * 类别名称
     */
    private String categoryName;
    /**
     * 创建者名称
     */
    private String adder;
}
