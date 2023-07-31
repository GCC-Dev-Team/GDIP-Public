package com.example.utils;

import java.time.LocalDateTime;
import java.time.Period;

public class TimeOverlapExample {

    public static boolean isTimeOverlap(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2) {
        // 检查两个时间段是否有重叠部分
        Period period1 = Period.between(start1.toLocalDate(), end1.toLocalDate());
        Period period2 = Period.between(start2.toLocalDate(), end2.toLocalDate());
        return (start1.isBefore(end2) && end1.isAfter(start2)) || period1.equals(period2);
    }
}
    