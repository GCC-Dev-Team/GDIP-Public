package com.example.utils;

import java.util.regex.Pattern;

/**
 * @author L
 */
public class PhoneNumberRegularExpression {
    public static Boolean regularPhoneNumberPattern(String phoneNumber) {
        String regex="^(?:(?:\\+|00)86)?1[3-9]\\d{9}$";
       return Pattern.matches(regex, phoneNumber);//表达式是否满足正则表达式
    }
}
