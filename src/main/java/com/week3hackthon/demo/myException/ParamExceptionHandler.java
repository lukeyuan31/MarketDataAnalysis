package com.week3hackthon.demo.myException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @Classname ParamExceptionHandler
 * @Description TODO
 * @Date 2021/8/19 9:05
 * @Created by rou
 */
@ControllerAdvice
public class ParamExceptionHandler {
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity handlerParamException(MethodArgumentTypeMismatchException ex){
        ErrorCodeVO errorCodeVO = new ErrorCodeVO("400",ex.getMessage(),ex.getParameter().getParameterName());
        return ResponseEntity.badRequest().body(errorCodeVO);
    }
    @ExceptionHandler(ParamOutOfRangeException.class)
    public ResponseEntity handlerParamOutofRangeException(ParamOutOfRangeException ex){
        ErrorCodeVO errorCodeVO = new ErrorCodeVO("400",ex.getMessage(),ex.getParam());
        return ResponseEntity.badRequest().body(errorCodeVO);
    }
}
