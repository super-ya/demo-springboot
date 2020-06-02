package com.example.demo.exception;


import com.example.demo.util.Result;

public class GlobalBusinessException extends RuntimeException {

    protected Result result;

    public GlobalBusinessException() {
        super();
        result = Result.error();
    }

    public GlobalBusinessException(String message) {
        super(message);
        result = Result.error(message);
    }

    public GlobalBusinessException(String message, Throwable cause) {
        super(message, cause);
        result = Result.error(message);
    }

    public GlobalBusinessException(int code, Object data, String message, Throwable cause) {
        super(message, cause);
        result = Result.result(false, data, message, code);
    }

    public GlobalBusinessException(int code, Object data, String message) {
        super(message);
        result = Result.result(false, data, message, code);
    }

    public GlobalBusinessException(int code, Object data, Throwable cause) {
        super(cause);
        result = Result.result(false, data, getMessage(), code);
    }

    public GlobalBusinessException(int code, Object data) {
        super();
        result = Result.result(false, data, getMessage(),code);
    }

    public GlobalBusinessException(Throwable cause) {
        super(cause);
        result = Result.error(getMessage());
    }

    protected GlobalBusinessException(String message,
                                      Throwable cause,
                                      boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        result = Result.error(getMessage());
    }
}