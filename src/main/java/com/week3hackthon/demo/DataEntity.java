package com.week3hackthon.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Collection;
import java.util.PrimitiveIterator;

//MongoDB结构：database: sstore collection: testdb

@Document(collection="stock_price")
public class DataEntity implements Serializable {
    //private Long id;
    @Field("Id")
    private String id;
    @Field("Date")
    private String date;
    @Field("Open")
    private String open;
    @Field("High")
    private String high;
    @Field("Low")
    private String low;
    @Field("Volume")
    private String volume;
    @Field("Name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DataEntity{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", open='" + open + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", volume='" + volume + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    //    @Override
//    public String toString() {
//        return "DataEntity{" +
//                "rowId='" + rowId + '\'' +
//                ", orderId='" + orderId + '\'' +
//                ", orderDate='" + orderDate + '\'' +
//                ", shipData='" + shipData + '\'' +
//                ", shipMode='" + shipMode + '\'' +
//                ", customerID='" + customerID + '\'' +
//                ", customerName='" + customerName + '\'' +
//                ", Segment='" + Segment + '\'' +
//                ", country='" + country + '\'' +
//                ", city='" + city + '\'' +
//                ", state='" + state + '\'' +
//                ", postalCode='" + postalCode + '\'' +
//                ", region='" + region + '\'' +
//                ", productID='" + productID + '\'' +
//                ", Category='" + Category + '\'' +
//                ", subCategory='" + subCategory + '\'' +
//                ", productName='" + productName + '\'' +
//                '}';
//    }
}
