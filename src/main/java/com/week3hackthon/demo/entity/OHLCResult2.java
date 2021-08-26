package com.week3hackthon.demo.entity;

/**
 * @Classname OHLCResult2
 * @Description TODO
 * @Date 2021/8/13 11:10
 * @Created by rou
 */
public class OHLCResult2 {
    String name;
    OHLCSub ohlcSub;
    Integer week;
    Integer year;
    Integer month;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OHLCSub getOhlcSub() {
        return ohlcSub;
    }

    public void setOhlcSub(OHLCSub ohlcSub) {
        this.ohlcSub = ohlcSub;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
