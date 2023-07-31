package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateRequest {
    /**
     * 活动标题
     */
    private String activityTitle;

    /**
     *活动内容的描述
     */
    private String activityDescription;
    /**
     *活动人数
     */
    private Integer numberOfParticipants;

    /**
     *活动地点
     */
    private String location;

    /**
     *开始时间
     */
    private String startTime;

    /**
     *结束时间
     */
    private String endTime;

}
