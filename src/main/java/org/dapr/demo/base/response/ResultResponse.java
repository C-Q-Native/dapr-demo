package org.dapr.demo.base.response;
/**
 * 响应结果封装
 */

public class ResultResponse {

    // 只返回状态
    public static Result getSuccessResult() {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        result.setMessage(ResultCode.SUCCESS);
        return result;
    }

    // 成功返回数据
    public static Result getSuccessResult(Object data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(ResultCode.SUCCESS)
                .setData(data);
    }

    // 失败
    public static Result getFailResult(String message) {
        return new Result()
                .setCode(ResultCode.INTERNAL_SERVER_ERROR)
                .setMessage(message)
                .setData(null);
    }

    // 失败
    public static Result getFailResult(Integer code, String message) {
        return new Result()
                .setCode(code)
                .setMessage(message)
                .setData(null);
    }

    public static Result getFailResult(ResultCode code) {
        return new Result()
                .setCode(code.getResultCode())
                .setMessage(code.getResultMsg())
                .setData(null);
    }

    public static Result getFailResult(Integer code, String message, Object data) {
        return new Result()
                .setCode(code)
                .setMessage(message)
                .setData(data);
    }

    /**
     * 失败
     */
    public static Result getFailResult(BaseErrorInfoInterface errorInfo) {
        return new Result()
                .setCode(errorInfo.getResultCode())
                .setMessage(errorInfo.getResultMsg())
                .setData(null);
    }

}