package com.example.demo.util;

/**
 * 响应客户端的结果集封装
 *
 * @author HUANG
 * @create 2018/03/15 13:57
 */
public class Result {
    private boolean success = true;
    private String message;
    private Object data;
    private int code = ResultCode.SUCCESS;


    /*静态方法*/

    public static Result result(boolean success, Object data, String message, int code) {
        Result result = new Result();
        result.setSuccess(success);
        result.setData(data);
        result.setMessage(message);
        result.setCode(code);
        return result;
    }

    public static Result result(boolean success, Object data, int code) {
        return result(success, data, null, code);
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setData(data);
        return result;
    }

    public static Result success(Object data, String message) {

        Result result = new Result();
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    public static Result successMessage(String message) {
        Result result = new Result();
        result.setMessage(message);
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR);
        return result;
    }

    public static Result error(String errorMsg, Object data) {
        Result error = error(errorMsg);
        error.setData(data);
        return error;
    }

    public static Result error(String errorMsg) {
        Result error = error();
        error.setMessage(errorMsg);
        return error;
    }


    public static Result success() {
        return new Result();
    }


    private Result() {
        super();
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }
}
