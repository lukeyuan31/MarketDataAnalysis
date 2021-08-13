package com.week3hackthon.demo;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @Classname StockRepository
 * @Description TODO
 * @Date 2021/8/12 15:04
 * @Created by rou
 */
public interface StockRepository {
    List<StockDateEntity>sortStocks(int desc, int n, int type, String startDate,String endDate);
    List<OHLCResult2>aggStock(String name,String startDate,String endDate,int by) throws ParseException;

}
