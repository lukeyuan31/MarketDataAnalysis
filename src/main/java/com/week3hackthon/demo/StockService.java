package com.week3hackthon.demo;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.awt.*;
import java.security.Key;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.week3hackthon.demo.Constants.ENDDATESTR;
import static com.week3hackthon.demo.Constants.STARTDATESTR;

/**
 * @Classname StockService
 * @Description TODO
 * @Date 2021/8/12 15:05
 * @Created by rou
 */


@Service
public class StockService implements StockRepository {
    @Resource
    private MongoTemplate mongoTemplate;
    @Override
    public List<StockDateEntity> sortStocks(int desc, int n, int type, String startDate, String endDate) {
        Query query = new Query(Criteria.where("Date").lte(endDate).gte(startDate));
        String t ="get"+ OHLC.values()[type].name();
        List<StockDateEntity> stockDateEntities = mongoTemplate.find(query, StockDateEntity.class);
        List<StockDateEntity> result = stockDateEntities.stream().filter(stockDateEntity -> stockDateEntity.getOpen()!=null).collect(Collectors.groupingBy(StockDateEntity::getName))
                .entrySet().stream().map(entry->{
                    String key = entry.getKey();
                    List<StockDateEntity>value = entry.getValue();
                    OptionalDouble open = value.stream().mapToDouble(StockDateEntity::getOpen).average();
                    OptionalDouble high = value.stream().mapToDouble(StockDateEntity::getHigh).average();
                    OptionalDouble low= value.stream().mapToDouble(StockDateEntity::getLow).average();
                    OptionalDouble close= value.stream().mapToDouble(StockDateEntity::getClose).average();
                    OptionalDouble volume = value.stream().mapToInt(StockDateEntity::getVolume).average();
                    StockDateEntity stockDateEntity = new StockDateEntity();
                    stockDateEntity.setName(key);
                    stockDateEntity.setOpen(open.getAsDouble());
                    stockDateEntity.setClose(close.getAsDouble());
                    stockDateEntity.setHigh(high.getAsDouble());
                    stockDateEntity.setLow(low.getAsDouble());
                    stockDateEntity.setVolume((int)volume.getAsDouble());
                    return stockDateEntity;
                }).collect(Collectors.toList());
        //result.stream().sorted(Comparator.comparing(StockDateEntity::getVolume)).limit(n);
        List<StockDateEntity> stockDateEntityList=new ArrayList<>();
        switch (type){
            case 0:
                switch (desc){
                    case 0:
                        stockDateEntityList=result.stream().sorted(Comparator.comparing(StockDateEntity::getOpen)).limit(n).collect(Collectors.toList());
                        break;
                    case 1:
                        stockDateEntityList=result.stream().sorted(Comparator.comparing(StockDateEntity::getOpen).reversed()).limit(n).collect(Collectors.toList());
                }
                break;
            case 1:
                switch (desc){
                    case 0:
                        stockDateEntityList=result.stream().sorted(Comparator.comparing(StockDateEntity::getHigh)).limit(n).collect(Collectors.toList());
                        break;
                    case 1:
                        stockDateEntityList=result.stream().sorted(Comparator.comparing(StockDateEntity::getHigh).reversed()).limit(n).collect(Collectors.toList());
                        break;
                }
                break;
            case 2:
                switch (desc){
                    case 0:
                        stockDateEntityList=result.stream().sorted(Comparator.comparing(StockDateEntity::getLow)).limit(n).collect(Collectors.toList());
                        break;
                    case 1:
                        stockDateEntityList=result.stream().sorted(Comparator.comparing(StockDateEntity::getLow).reversed()).limit(n).collect(Collectors.toList());
                        break;
                }
                break;
            case 3:
                switch (desc){
                    case 0:
                        stockDateEntityList=result.stream().sorted(Comparator.comparing(StockDateEntity::getClose)).limit(n).collect(Collectors.toList());
                        break;
                    case 1:
                        stockDateEntityList=result.stream().sorted(Comparator.comparing(StockDateEntity::getClose).reversed()).limit(n).collect(Collectors.toList());
                        break;
                }
                break;
            case 4:
                switch (desc){
                    case 0:
                        stockDateEntityList=result.stream().sorted(Comparator.comparing(StockDateEntity::getVolume)).limit(n).collect(Collectors.toList());
                        break;
                    case 1:
                        stockDateEntityList=result.stream().sorted(Comparator.comparing(StockDateEntity::getVolume).reversed()).limit(n).collect(Collectors.toList());
                        break;
                }
                break;
        }
//        Criteria criteria = Criteria.where("Date").lte(endDate).gte(startDate);
//
//        String t ="avg"+ OHLC.values()[type].name();
//        Sort.Direction d = desc?Sort.Direction.DESC:Sort.Direction.ASC;
//
//        Aggregation aggregation = Aggregation.newAggregation(
//                Aggregation.project("Open","High","Low","Close","Volume","Name"),
//                Aggregation.match(criteria),
//                Aggregation.group("Name").first("Name").as("Name")
//                        .avg("Open").as("avgOpen")
//                .avg("Close").as("avgClose")
//                .avg("High").as("avgHigh")
//                .avg("Low").as("avgLow")
//                .avg("Volume").as("avgVolume"),
//                Aggregation.sort(d,t),
//                Aggregation.limit(n)
//        );
//        AggregationResults<SortStockEntity> aggregationResults = mongoTemplate.aggregate(aggregation,StockDateEntity.class,SortStockEntity.class);
//        return aggregationResults.getMappedResults();
        return stockDateEntityList;
    }
}
