package com.week3hackthon.demo;


import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private DataDAO dataDAO;

    @Test
    public void findDemoByIdTest() {

        DataEntity dataEntity = dataDAO.findDataById("California");

        if (dataEntity!=null){
            System.out.println("Success");
            //System.out.println(dataEntity.getState());
            System.out.println(dataEntity.toString());
        }else {
            System.out.println("fail");
        }
    }
}
