package com.example.demo.exception;


import com.example.demo.util.ResultCode;

/**
 * @author zkq
 * @version 1.0
 * @date 2019/5/7 11:15
 */
public class ParameterExecption extends GlobalBusinessException {

    private static final int CODE = ResultCode.ILLEGAL_PARAMETER;

    public ParameterExecption() {
        super(CODE, null, "参数错误");
    }

    public ParameterExecption(String message) {
        super(CODE, null, message);
    }

    public ParameterExecption(String message, Object data) {
        super(CODE, data, message);
    }


    public ParameterExecption(String message, Throwable cause) {
        super(CODE, null, message, cause);
    }
}
