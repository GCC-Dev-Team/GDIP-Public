package com.example.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final String DEFAULT_PATTERN = "yyyy/MM/dd HH:mm:ss";

    public static Date stringToDate(String dateStr, String pattern)  {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public static Date stringToDate(String dateStr) {
        return stringToDate(dateStr, DEFAULT_PATTERN);
    }

}
