package com.week3hackthon.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api")
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
}
