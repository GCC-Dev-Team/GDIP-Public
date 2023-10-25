package com.example.model.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAuditsVO {
    /**
     * 状态
     */
    private Integer state;

    /**
     *不通过原因
     */
    private String remarks;

    /**
     *审核学生证照片
     */
    private String auditImageUrl;
}
