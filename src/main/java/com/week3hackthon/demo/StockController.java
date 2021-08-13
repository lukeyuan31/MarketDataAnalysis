package com.week3hackthon.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.week3hackthon.demo.Constants.ENDDATESTR;
import static com.week3hackthon.demo.Constants.STARTDATESTR;


/**
 * @Classname StockController
 * @Description TODO
 * @Date 2021/8/12 15:00
 * @Created by rou
 */
@RestController
@RequestMapping(path = "api")
public class StockController {
    @Autowired
    StockRepository stockRepository;
//    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//    private Date sDate = dateFormat.parse(STARTDATESTR);
//    private Date eDate = dateFormat.parse(ENDDATESTR);

    public StockController() throws ParseException {
    }


    @GetMapping("/sortStocks")
    public List<StockDateEntity> sortStock(@RequestParam(name = "desc") int Desc,
                                           @RequestParam(name = "n") int n,
                                           @RequestParam(name="type")int type,
                                           @RequestParam(name="startDate",defaultValue = STARTDATESTR)String startDate,
                                           @RequestParam(name="endDate",defaultValue = ENDDATESTR)String endDate){
        return stockRepository.sortStocks(Desc,n,type,startDate,endDate);
    }

}
