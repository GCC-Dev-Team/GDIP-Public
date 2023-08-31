package com.example.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskSmallVO {

    private String id;

    private String activityTitle;

    private String imageUrl;

    private Double price;

    private Date startTime;

    private Date endTime;

    private int people;
}
