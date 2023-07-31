package com.example.utils;

import java.time.*;
import java.util.Date;

public class DateTranslation {
    public static Date localDateTimeTransformDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.toInstant(ZoneOffset.ofHours(8)));
    }
    public static LocalDateTime DateTranslationLocalDateTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.of("Asia/Shanghai"); // 以上海时区为例
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        return zonedDateTime.toLocalDateTime();
    }
}
