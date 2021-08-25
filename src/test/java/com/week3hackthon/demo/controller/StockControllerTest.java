package com.week3hackthon.demo.controller;

import com.week3hackthon.demo.service.StockService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Classname StockControllerTest
 * @Description TODO
 * @Date 2021/8/25 10:27
 * @Created by rou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StockControllerTest {
    @Autowired
    private MockMvc mockMvc;



    @Test
    void sortStock() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/sortStocks?desc=1&n=10&type=3&startDate=2010-01-01&endDate=2011-01-01"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getStatus());
    }

    @Test
    void aggStock() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/aggStocks/AAPL?by=0&startDate=2010-01-01&endDate=2017-01-01"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getStatus());
    }
}