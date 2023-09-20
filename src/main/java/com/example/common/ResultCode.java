package com.example.common;

import lombok.Getter;

//状态码枚举
@Getter
public enum ResultCode {

    /*成功状态码*/
    SUCCESS(1,"成功"),

    /*参数错误：1001-1999*/
    PARAM_IS_INVALID(1001,"参数无效"),
    PARAM_IS_BLANK(1002,"参数为空"),
    PARAM_TYPE_BIND_ERROR(1003,"参数类型错误"),
    PARAM_NOT_COMPLETE(1004,"参数缺失"),
    PARAM_ERROR(1005,"修改失败，请检查账号是否正确"),

    /*用户错误：2001-2900*/
    USER_NOT_LOGGED_IN(2001,"用户未登录"),
    USER_LOGIN_ERROR(2002,"登陆错误"),
    USER_ACCOUNT_FORBIDDEN(2003,"账号已被禁用"),
    USER_NOT_EXIST(2004,"用户不存在"),
    USER_HAS_EXISTED(2005,"用户已存在"),
    USER_TOKEN_IS_INVALID(2006,"用户token无效"),
    USER_TOKEN_IS_BLANK(2007,"用户token为空"),
    USER_VERIFY_ERROR(2008,"验证码校验失败，请重新获取"),
    USER_VERIFY_EMPTY(2009,"验证码不存在，请重新发送"),
    USER_AVATAR_UPLOAD_ERROR(2010,"用户头像上传失败"),
    /* 商品相关错误 */
    PRODUCT_RELEASE_ERROR(3001,"商品发布异常"),
    PRODUCT_AMEND_ERROR(3002,"该活动并非你发布的!或者商品已被拍下！"),

    PRODUCT_NULL_ERROR(3003,"该账号查不到该商品是该账号的!"),

    /*服务器内部错误*/
    INTERNAL_ERROR(5001,"服务器内部错误"),
    PARAMETER_CONVERSION_ERROR(5002,"参数不合法，转换错误"),
    SYSTEM_ERROR(5003,"系统错误"),
    SYSTEM_NOT_SUPPORT(5004,"功能开发中，敬请期待"),

    /*地址*/
    ADDRESS_REPEAT(6001,"地址重复添加"),
    ADDRESS_DELETE_ERROR(6002,"此地址删除失败，非本账号的"),

    /*收藏*/
    FAVORITE_ADD_ERROR(8001,"你已经收藏该商品了！"),
    FAVORITE_DELETE_ERROR(8002,"请先收藏该商品"),

    /**
     * 关注
     */
    FOLLOWER_ADD_ERROR(9001,"关注成功!"),
    FOLLOWER_REPEAT(9002,"已经关注了，请勿重复关注"),
    FOLLOWER_NULL_USER(9003,"没有关注该用户"),

    /*任务*/
    TASK_NULL_ID(1101,"任务id为空!"),
    TASK_ERROR_NUMBER_PEOPLE(1102,"任务人数已满或者已经结束"),

    TASK_ERROR_REPEAT(1103,"你已经报名了!,请勿再次报名"),
    TASK_ERROR_REPEAT_TIME(1104,"你已经在此时间段参加了另一个任务了！"),
    TASK_SUCCESS_TAKE_PART_IN(1105,"报名成功"),
    TASK_SUCCESS_DELETE(1106,"成功删除！"),
    TASK_ERROR_DELETE(1107,"你无权删除该活动"),

    /*登录*/
    LOGIN_ERROR_CODE(1201,"code为null！"),
    LOGIN_ERROR_APPID(1202,"appid出错!"),
    /*用户*/

    USER_ERROR_NULL(1301,"没有找到用户"),
    USER_ERROR_UPDATE_PASSWORD(1302,"账户或者密码出错！"),
    USER_ERROR_UPDATE_SCHOOL_PASSWORD(1303,"学生账户或者密码出错！"),
    USER_SUCCESS_UPDATE_PASSWORD(1304,"成功修改密码");


    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 接口调用提示信息
     */
    private final String message;

    ResultCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }

}