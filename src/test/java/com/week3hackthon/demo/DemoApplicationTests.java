package com.week3hackthon.demo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private DataRepository dataRepository;

    @Test
    public void findDemoByIdTest() {

        DataEntity dataEntity = dataRepository.findDataById("California");

        if (dataEntity!=null){
            System.out.println("Success");
            //System.out.println(dataEntity.getState());
            System.out.println(dataEntity.toString());
        }else {
            System.out.println("fail");
        }
    }
}
