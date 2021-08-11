package com.week3hackthon.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Collection;

//MongoDB结构：database: sstore collection: testdb

@Document(collection="testdb")
public class DataEntity implements Serializable {
    @Id
    //private Long id;
    //这里的所有column只用到了country和state做测试用，postman只返回这两个
    //TODO: 更换数据库，整理column
    private String rowId;
    private String orderId;
    private String orderDate;
    private String shipData;
    private String shipMode;
    private String customerID;
    private String customerName;
    private String Segment;
    @Field("Country")
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private String city;
    @Field("State")
    private String state;
    private String postalCode;
    private String region;
    private String productID;
    private String Category;
    private String subCategory;
    private String productName;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return "DataEntity{" +
                "rowId='" + rowId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", shipData='" + shipData + '\'' +
                ", shipMode='" + shipMode + '\'' +
                ", customerID='" + customerID + '\'' +
                ", customerName='" + customerName + '\'' +
                ", Segment='" + Segment + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", region='" + region + '\'' +
                ", productID='" + productID + '\'' +
                ", Category='" + Category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }
}
