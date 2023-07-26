package com.example.common;

//状态码枚举
public enum ResultCode {

    /*成功状态码*/
    SUCCESS(1,"成功"),

    /*参数错误：1001-1999*/
    PARAM_IS_INVALID(1001,"参数无效"),
    PARAM_IS_BLANK(1002,"参数为空"),
    PARAM_TYPE_BIND_ERROR(1003,"参数类型错误"),
    PARAM_NOT_COMPLETE(1004,"参数缺失"),

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

    /*服务器内部错误*/
    INTERNAL_ERROR(5001,"服务器内部错误"),
    PARAMETER_CONVERSION_ERROR(5002,"参数不合法，转换错误"),
    SYSTEM_ERROR(5003,"系统错误"),
    SYSTEM_NOT_SUPPORT(5004,"功能开发中，敬请期待");


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

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}