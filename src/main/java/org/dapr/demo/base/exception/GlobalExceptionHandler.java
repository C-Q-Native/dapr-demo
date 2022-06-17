package org.dapr.demo.base.exception;

import org.dapr.demo.base.response.Result;
import org.dapr.demo.base.response.ResultResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = ResponseException.class)
    @ResponseBody
    public Result bizExceptionHandler(HttpServletRequest req, ResponseException e){
        if (e.getData() != null) {
            return ResultResponse.getFailResult(e.getErrorCode(),e.getErrorMsg(), e.getData());
        }
        return ResultResponse.getFailResult(e.getErrorCode(),e.getErrorMsg());
    }

    @ExceptionHandler(value =Exception.class)
    public Result exceptionHandler(Exception e){
        return ResultResponse.getFailResult(500,"未知异常！原因是:" + e.getMessage());
    }
}