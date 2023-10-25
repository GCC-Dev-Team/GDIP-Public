package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessStudentCertification {

    private String auditId;

    /**
     * 处理结果code，1表示成功通过，2表示失败
     */

    private Integer processCode;

    /**
     * 失败原因
     */
    private String failureReason;
}
