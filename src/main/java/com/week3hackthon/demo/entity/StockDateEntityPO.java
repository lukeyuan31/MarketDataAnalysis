package com.week3hackthon.demo.entity;

import java.util.Calendar;
import java.util.Date;

/**
 * @Classname StockDateEntityPO
 * @Description TODO
 * @Date 2021/8/13 11:17
 * @Created by rou
 */
public class StockDateEntityPO {
    String name;
    Date StartDate;
    Double open;
    Integer volume;
    Integer month;
    Integer year;
    Integer week;

    public StockDateEntityPO(Date startDate){
        this.StartDate = startDate;
        Calendar ca = Calendar.getInstance();
        ca.setTime(startDate);
        this.month = ca.get(Calendar.MONTH);
        this.year = ca.get(Calendar.YEAR);
        this.week = ca.get(Calendar.WEEK_OF_YEAR);


    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }


    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }
}
