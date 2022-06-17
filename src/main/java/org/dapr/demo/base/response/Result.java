package org.dapr.demo.base.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonSerialize
public class Result<T> {

    private Integer code;

    private String message;

    private T data;

    public Result setCode(ResultCode resultCode){
        this.code = resultCode.getResultCode();
        return this;
    }

    public Result setCode(Integer code){
        this.code = code;
        return this;
    }

    public Result setMessage(ResultCode resultCode){
        this.message = resultCode.getResultMsg();
        return this;
    }

    public Result setMessage(String message){
        this.message = message;
        return this;
    }

    public Result setData(T data){
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public Result(){}

    public Result(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Result(ResultCode resultCode, T data){
        this.code = resultCode.getResultCode();
        this.message = resultCode.getResultMsg();
        this.data = data;
    }

    public Result(Integer code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(ResultCode resultCode){
        this.code = resultCode.getResultCode();
        this.message = resultCode.getResultMsg();
    }

    public static <T> Result<T> success(){
        return new Result<T>(ResultCode.SUCCESS);
    }

    public static <T> Result<T> success(T data){
        return new Result<T>(ResultCode.SUCCESS, data);
    }

    public static <T> Result<T> fail(Integer code, String message){
        return new Result<T>(code, message);
    }

    public static <T> Result<T> fail(ResultCode resultCode){
        return new Result<T>(resultCode);
    }

}
