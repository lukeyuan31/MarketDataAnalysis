package com.week3hackthon.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api")
public class DataController {
    @Autowired
    DataDAOImpl dataDAO;

    @GetMapping
    public DataEntity getData(String state){
        System.out.println("Received get");
        DataEntity dataEntity=dataDAO.findDataById(state);
        System.out.println(dataEntity.toString());
        return dataEntity;
    }
}
