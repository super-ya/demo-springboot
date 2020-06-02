package com.example.demo.exception;
import com.example.demo.util.Result;
import com.example.demo.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static com.example.demo.exception.ExceptionMessage.Level.*;

/**
 * Project <yihao01-cron-scheduler>
 * Created by jorgezhong on 2019/5/6 16:24.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler({GlobalBusinessException.class})
    @ResponseBody
    public Result handleAbstractBusinessException(GlobalBusinessException e, HandlerMethod handler,
                                                  HttpServletRequest request) {
        log(e, handler, request, ERROR, true);
        System.out.println("=============catch GlobalBusinessException===========");
        return e.result;
    }


    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseBody
    public Result handleBindException(Exception e, HandlerMethod handler, HttpServletRequest request) {
        log(e, handler, request, DEBUG, false);

        List<ObjectError> errors = getErrors(e);
        List<String> causes = new ArrayList<>(errors.size());
        errors.forEach(error -> {
            if (error instanceof FieldError) {
                causes.add(((FieldError) error).getField() + " : " + error.getDefaultMessage());
            } else {
                causes.add(error.getDefaultMessage());
            }
        });
        return Result.result(false, causes, "参数错误", ResultCode.ILLEGAL_PARAMETER);
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleException(Exception e, HandlerMethod handler, HttpServletRequest request) {

        log(e, handler, request, ERROR, true);
        System.out.println("=============the Exception catch  RuntimeException===========");
        return Result.error("系统异常，请联系管理员");
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e,
                                                               HttpServletRequest request) {

        log(e, null, request, WARN, false);
        String message = MessageFormat.format("不支持''{0}''请求方式。支持{1}",
                                              e.getMethod(),
                                              Arrays.toString(e.getSupportedMethods()));
        return Result.result(false, null, message, ResultCode.FAILURE);
    }


    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleHttpRequestMethodNotSupportedException(HttpMessageNotReadableException e, HandlerMethod handler,
                                                               HttpServletRequest request) {

        log(e, handler, request, WARN, false);
        String message = MessageFormat.format("接口''{0}''无法读取请求参数,原因: {1}", request.getRequestURI(), e.getMessage());
        return Result.error(message);
    }

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleHttpRequestMethodNotSupportedException(HttpMediaTypeNotSupportedException e,
                                                               HandlerMethod handler,
                                                               HttpServletRequest request) {


        log(e, handler, request, INFO, false);
        String message = MessageFormat.format("接口''{0}'',不支持''{1}''类型的Content Type ",
                                              request.getRequestURI(),
                                              e.getContentType());
        return Result.result(false, null, message, ResultCode.FAILURE);
    }


    @ExceptionHandler({NoHandlerFoundException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handleNoHandlerFoundException(NoHandlerFoundException e, HttpServletRequest request) {
        log(e, null, request, DEBUG, false);
        return Result.result(false, null, "接口 [" + request.getRequestURI() + "] 不存在", ResultCode.NOT_FOUND);
    }


    private List<ObjectError> getErrors(Exception e) {
        if (e instanceof MethodArgumentNotValidException) {
            return ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
        } else if (e instanceof BindException) {
            return ((BindException) e).getBindingResult().getAllErrors();
        }
        return Collections.emptyList();
    }


    private void log(Exception e,
                     HandlerMethod handler,
                     HttpServletRequest request,
                     ExceptionMessage.Level level,
                     boolean printStack) {
        String message = getMessage(e, handler, request);

        if (handler != null) {
            ExceptionMessage annotation = handler.getMethodAnnotation(ExceptionMessage.class);
            if (annotation != null) {
                level = annotation.level();
                printStack = annotation.isPrintStack();
                message = annotation.value() + ", " + message;
            }
        }

        switch (level) {
            case INFO:
                if (printStack) {
                    LOGGER.info(message, e);
                } else {
                    LOGGER.info(message);
                }
                break;
            case DEBUG:
                if (printStack) {
                    LOGGER.debug(message, e);
                } else {
                    LOGGER.debug(message);
                }
                break;
            case ERROR:
                if (printStack) {
                    LOGGER.error(message, e);
                } else {
                    LOGGER.error(message);
                }
                break;
            case WARN:
                if (printStack) {
                    LOGGER.warn(message, e);
                } else {
                    LOGGER.warn(message);
                }
                break;
            default:
        }


    }

    private String getMessage(Exception e, HandlerMethod handler, HttpServletRequest request) {

        String message = e.getMessage();
        if (handler != null) {
            String format = "接口 [%s] 出现异常，方法：[%s.%s]，异常摘要：%s";
            message = String.format(format,
                                    request.getRequestURI(),
                                    handler.getBean().getClass().getName(),
                                    handler.getMethod().getName(),
                                    message);
        }
        return message;
    }

}
