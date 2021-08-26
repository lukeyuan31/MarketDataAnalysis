package com.week3hackthon.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Classname DataControllerTest
 * @Description TODO
 * @Date 2021/8/25 11:20
 * @Created by rou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class DataControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getData() throws Exception {
//http://localhost:8080/api?name=MMM
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api?name=MMM"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getStatus());
    }

    @Test
    void getAllByCompanyName() throws Exception {
        //http://localhost:8080/api/stocks?name=AAPL&startDate=2011-01-01&endDate=2012-01-1
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/stocks?name=AAPL&startDate=2011-01-01&endDate=2012-01-1"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getStatus());

    }

    @Test
    void getTotalVolumeofCompanies() throws Exception {
        //http://localhost:8080/api/topVolume
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/topVolume"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getStatus());

    }

    @Test
    void getOHLCagg() throws Exception {
        //http://localhost:8080/api/max?name=AAPL&startDate=2011-01-01&endDate=2012-01-01
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/max?name=AAPL&startDate=2011-01-01&endDate=2012-01-01"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getStatus());
    }

    @Test
    void queryStock() throws Exception {
        //http://localhost:8080/api/filter?maxClose=100.0&minClose=20.0&startDate=2010-01-01&endDate=2011-01-01
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/filter?maxClose=100.0&minClose=20.0&startDate=2010-01-01&endDate=2011-01-01"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getStatus());
    }
}