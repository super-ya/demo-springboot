package com.example.demo.exception;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExceptionMessage {
    String value() default "";

    boolean isPrintStack() default false;

    Level level() default Level.DEBUG;

    enum Level {
        /**
         * 
         */
        INFO,
        DEBUG,
        ERROR,
        WARN
    }
}