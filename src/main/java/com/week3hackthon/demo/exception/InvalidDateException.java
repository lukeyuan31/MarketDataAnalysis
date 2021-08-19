package com.week3hackthon.demo.exception;

public class InvalidDateException extends Exception{
    public InvalidDateException(){
        System.out.println("The date is invalid");
    }
}
