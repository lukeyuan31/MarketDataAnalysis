package com.week3hackthon.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping(path = "{companyName}")
    public List<DataEntity> getAllByCompanyName(@PathVariable("companyName") String name){
        return dataService.getAllByCompanyName(name);
    }

    @GetMapping(path = "topVolume")
    public List<DataEntity> getTotalVolumeofCompanies(){
        return dataService.getTopTenVolume();
    }
}
