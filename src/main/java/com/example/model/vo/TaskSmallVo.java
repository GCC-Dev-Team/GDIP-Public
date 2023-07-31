package com.example.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskSmallVo {

    private String id;

    private String title;

    private String url;

    private Date beginTime;

    private Date endTime;
}
