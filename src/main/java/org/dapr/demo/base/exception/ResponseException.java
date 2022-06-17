package org.dapr.demo.base.exception;

import org.dapr.demo.base.response.BaseErrorInfoInterface;
import org.dapr.demo.base.response.ResultCode;

public class ResponseException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    /**
     * 错误码
     */
    protected Integer errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    protected Object data;

    public ResponseException() {
        super();
    }

    public ResponseException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public ResponseException(Integer errorCode, String errorMsg) {
        super(String.valueOf(errorCode));
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ResponseException(Integer errorCode, String errorMsg, Object data) {
        super(String.valueOf(errorCode));
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public ResponseException(ResultCode code) {
        super(String.valueOf(code.getResultCode()));
        this.errorCode = code.getResultCode();
        this.errorMsg = code.getResultMsg();
    }

    public ResponseException(BaseErrorInfoInterface errorInfoInterface) {
        super(String.valueOf(errorInfoInterface.getResultCode()));
        this.errorCode = errorInfoInterface.getResultCode();
        this.errorMsg = errorInfoInterface.getResultMsg();
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getMessage() {
        return errorMsg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}

