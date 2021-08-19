package com.week3hackthon.demo.myException;

/**
 * @Classname ParamOutOfRangeException
 * @Description TODO
 * @Date 2021/8/19 9:29
 * @Created by rou
 */
public class ParamOutOfRangeException extends RuntimeException {
    private String param;
    public ParamOutOfRangeException(String message, String param) {
        super(message);
        this.param = param;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
