package com.example.model.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolVO {
    /**
     *名称
     */
    private String name;

    /**
     * 学校编号
     */
    private String school;
}
