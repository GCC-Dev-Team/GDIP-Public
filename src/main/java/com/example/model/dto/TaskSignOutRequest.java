package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskSignOutRequest {

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 签退码
     */
    private String signOutCode;
}
