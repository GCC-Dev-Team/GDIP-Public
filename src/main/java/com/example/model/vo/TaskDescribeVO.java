package com.example.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDescribeVO {
    private String id;
    private String activityTitle;
    private String activityDescription;
    private String imageUrl;
    private Integer numberOfParticipants;
    private String location;
    private Date startTime;
    private Date endTime;
    private String signOutCode;
    private Integer status;
    private Double price;
    private String userId;
    private String userImage;
    private Date updateTime;

    private String userName;

    private int people;
}
