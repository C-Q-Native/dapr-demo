package org.dapr.demo.base.response;

public enum ResultCode implements BaseErrorInfoInterface {
    ///错误码定义 数据操作错误定义
    SUCCESS(200, "SUCCESS!"), //请求成功

    //////通用格式错误
    BODY_NOT_MATCH(400,"BODY_NOT_MATCH"),                   //请求的数据格式不符!
    SIGNATURE_NOT_MATCH(401,"SIGNATURE_NOT_MATCH"),         //请求的数字签名不匹配!
    NOT_FOUND(404, "NOT_FOUND"),                            //未找到该资源!
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR"),    //服务器内部错误!
    SERVER_BUSY(503,"SERVER_BUSY"),                         //服务器正忙，请稍后再试!
    BAD_REQUEST_PARAMS(100001,"BAD_REQUEST_PARAMS"),        //参数格式错误!

    ;
    /** 错误码 */
    private Integer resultCode;

    /** 错误描述 */
    private String resultMsg;

    ResultCode(Integer resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public Integer getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
