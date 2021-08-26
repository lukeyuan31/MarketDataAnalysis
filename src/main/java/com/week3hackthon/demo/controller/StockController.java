package com.week3hackthon.demo.controller;

import com.week3hackthon.demo.entity.OHLCResult2;
import com.week3hackthon.demo.entity.StockDateEntity;
import com.week3hackthon.demo.repository.StockRepository;
import com.week3hackthon.demo.myException.ParamOutOfRangeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

import static com.week3hackthon.demo.constants.Constants.ENDDATESTR;
import static com.week3hackthon.demo.constants.Constants.STARTDATESTR;


/**
 * @Classname StockController
 * @Description TODO
 * @Date 2021/8/12 15:00
 * @Created by rou
 */
@RestController
@RequestMapping(path = "api")
@CrossOrigin
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
        if (Desc>1||Desc<0) throw new ParamOutOfRangeException("desc","out of range");
        if (type>4||type<0) throw new ParamOutOfRangeException("type","out of range");
        return stockRepository.sortStocks(Desc,n,type,startDate,endDate);
    }
    @GetMapping(path = "/aggStocks/{companyName}")
    public List<OHLCResult2> aggStock(@PathVariable("companyName") String name,
                                      @RequestParam(name = "by") int by,
                                      @RequestParam(name="startDate",defaultValue = STARTDATESTR)String startDate,
                                      @RequestParam(name="endDate",defaultValue = ENDDATESTR)String endDate) throws ParseException {
        if (by>4||by<0) throw new ParamOutOfRangeException("by","out of range");
        return stockRepository.aggStock(name,startDate,endDate,by);
    }

}
