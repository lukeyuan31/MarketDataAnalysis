package com.week3hackthon.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Collection;
import java.util.PrimitiveIterator;

//MongoDB结构：database: sstore collection: testdb

@Document(collection="stock_price")
public class StockDateEntity implements Serializable {
//    @Id
//    //private Long id;
//    @Field("Id")
//    private String id;
    @Field("Date")
    private String date;



    @Field("Open")
    private Double open;
    @Field("High")
    private Double high;
    @Field("Low")
    private Double low;
    @Field("Close")
    private Double close;
    @Field("Volume")
    private Integer volume;
    @Field("Name")
    private String name;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
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
//                "id='" + id + '\'' +
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
