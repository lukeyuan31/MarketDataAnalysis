package com.week3hackthon.demo.myException;

/**
 * @Classname ErrorCodeVO
 * @Description TODO
 * @Date 2021/8/19 9:07
 * @Created by rou
 */
public class ErrorCodeVO {
    private String code;
    private String message;
    private String parameter;

    public ErrorCodeVO(String code, String message, String parameter) {
        this.code = code;
        this.message = message;
        this.parameter = parameter;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
