package com.example.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskVO {
    /**
     *
     */
    private String id;

    /**
     *
     */
    private String activityTitle;

    /**
     *
     */
    private String activityDescription;

    /**
     *
     */
    private String imageUrl;

    /**
     *
     */
    private Integer numberOfParticipants;

    /**
     *
     */
    private String location;

    /**
     *
     */
    private Date startTime;

    /**
     *
     */
    private Date endTime;

    /**
     *
     */
    private String signOutCode;

    /**
     *
     */
    private Integer isCompleted;

    /**
     *
     */
    private String initiator;

    /**
     *
     */
    private Date createdTime;

    /**
     *
     */
    private Date updatedTime;

}
