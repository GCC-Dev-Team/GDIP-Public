package com.example.common;


import lombok.Data;


import java.io.Serializable;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -8624400726954440585L;

    private Integer code;
    private String message;
    private T data;

    public Result() {
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public void setResultCode(ResultCode resultCode){
        this.setCode(resultCode.getCode());
        this.setMessage(resultCode.getMessage());
    }

    //返回成功
    public static <T> Result<T> success(){
        return createResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    //返回成功
    public static <T> Result<T> success(T data){
        return createResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    //返回失败
    public static <T> Result<T> failure(ResultCode resultCode){
        return createResult(resultCode.getCode(), resultCode.getMessage(), null);
    }

    //返回失败
    public static <T> Result<T> failure(ResultCode resultCode,T data){
        return createResult(resultCode.getCode(), resultCode.getMessage(), data);
    }

    public static <T> Result<T> createResult(Integer code, String message, T data){
        return new Result<>(code, message, data);
    }
}