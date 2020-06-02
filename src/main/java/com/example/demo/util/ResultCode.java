package com.example.demo.util;

public interface ResultCode {
    /**
     * 成功
     */
    int SUCCESS = 0;

    /**
     * 系统错误
     */
    int ERROR = 1;

    /**
     * 操作失败
     */
    int FAILURE = 2;

    /**
     * 找不到指令
     */
    int NOT_FOUND = 3;

    /**
     * 参数异常
     */
    int ILLEGAL_PARAMETER = 4;
}
