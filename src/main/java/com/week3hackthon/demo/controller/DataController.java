package com.week3hackthon.demo.controller;

import com.week3hackthon.demo.entity.DataEntity;
import com.week3hackthon.demo.service.DataService;
import com.week3hackthon.demo.entity.TopVolumeEntity;
import com.week3hackthon.demo.exception.InvalidDateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api")
@CrossOrigin
public class DataController {
    @Autowired
    DataService dataService;

    @GetMapping
    public DataEntity getData(String name){
        //System.out.println("Received get");
        DataEntity dataEntity=dataService.findDataById(name);
        System.out.println(dataEntity.toString());
        return dataEntity;
    }

    @GetMapping(path = "stocks")
    public List<DataEntity> getAllByCompanyName(String name,String startDate,String endDate) throws InvalidDateException, ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date leftDate=simpleDateFormat.parse("2006-01-01");
        Date rightDate=simpleDateFormat.parse("2016-12-21");
        Date start=simpleDateFormat.parse(startDate);
        if (start.before(leftDate) || simpleDateFormat.parse(endDate).after(rightDate)){
            throw new InvalidDateException();
        }else {
            return dataService.getAllByCompanyName(name, startDate, endDate);
        }
    }

    @GetMapping(path = "topVolume")
    public List<TopVolumeEntity> getTotalVolumeofCompanies(){
        return dataService.getTenVolume();
    }

    @GetMapping(path = "max")
    public DataEntity getOHLCagg(String name,String startDate,String endDate){
        return dataService.aggStocks(name,startDate,endDate);
    }

    @GetMapping(path = "filter")
    public List<DataEntity> queryStock(String name,Double maxClose,Double minClose,String startDate,String endDate){
        return dataService.queryStocks(name,maxClose,minClose,startDate,endDate);
    }
}
