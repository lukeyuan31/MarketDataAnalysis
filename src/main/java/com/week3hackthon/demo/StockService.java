package com.week3hackthon.demo;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

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
        // service test
        return stockDateEntityList;
    }

    @Override
    public List<OHLCResult2> aggStock(String name, String startDate, String endDate, int by) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Query query = new Query(Criteria.where("Date").lte(endDate).gte(startDate).and("Name").is(name));
        List<StockDateEntity> stockDateEntities = mongoTemplate.find(query, StockDateEntity.class);
        List<StockDateEntityPO>result = stockDateEntities.stream().map(t->{
            Date date=new Date();
            try {
                date = simpleDateFormat.parse(t.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            StockDateEntityPO po = new StockDateEntityPO(date);
            po.setName(t.getName());
            po.setOpen(t.getOpen());
            po.setVolume(t.getVolume());
            return po;

        }).collect(Collectors.toList());
        List<OHLCResult2> result2 = new ArrayList<>();
        switch (by){
            case 0:
                         result2 = result.stream().collect(Collectors.groupingBy(StockDateEntityPO::getWeek))
                        .entrySet().stream().map(entry->{
                            Integer key = entry.getKey();
                            List<StockDateEntityPO>value = entry.getValue();
                            OptionalDouble openMax = value.stream().mapToDouble(StockDateEntityPO::getOpen).max();
                            OptionalDouble openMin = value.stream().mapToDouble(StockDateEntityPO::getOpen).min();
                            OptionalDouble openAvg = value.stream().mapToDouble(StockDateEntityPO::getOpen).average();
                            OptionalDouble volumeMax = value.stream().mapToDouble(StockDateEntityPO::getVolume).max();
                            OptionalDouble volumeMin = value.stream().mapToDouble(StockDateEntityPO::getVolume).min();
                            OptionalDouble volumeAvg = value.stream().mapToDouble(StockDateEntityPO::getVolume).average();
                            OHLCResult2 ohlcResult2 = new OHLCResult2();
                            ohlcResult2.setName(name);
                            OHLCSub ohlcSub = new OHLCSub();
                            ohlcSub.setOpenAvg(openAvg.getAsDouble());
                            ohlcSub.setOpenMAx(openMax.getAsDouble());
                            ohlcSub.setOpenMin(openMin.getAsDouble());
                            ohlcSub.setVolumeAvg((int)volumeAvg.getAsDouble());
                            ohlcSub.setVolumeMax((int)volumeMax.getAsDouble());
                            ohlcSub.setVolumeMin((int)volumeMin.getAsDouble());
                            ohlcResult2.setOhlcSub(ohlcSub);
                            ohlcResult2.setWeek(key);
                            return ohlcResult2;
                        }).collect(Collectors.toList());
                break;
            case 1:
                        result2 = result.stream().collect(Collectors.groupingBy(StockDateEntityPO::getMonth))
                        .entrySet().stream().map(entry->{
                            Integer key = entry.getKey();
                            List<StockDateEntityPO>value = entry.getValue();
                            OptionalDouble openMax = value.stream().mapToDouble(StockDateEntityPO::getOpen).max();
                            OptionalDouble openMin = value.stream().mapToDouble(StockDateEntityPO::getOpen).min();
                            OptionalDouble openAvg = value.stream().mapToDouble(StockDateEntityPO::getOpen).average();
                            OptionalDouble volumeMax = value.stream().mapToDouble(StockDateEntityPO::getVolume).max();
                            OptionalDouble volumeMin = value.stream().mapToDouble(StockDateEntityPO::getVolume).min();
                            OptionalDouble volumeAvg = value.stream().mapToDouble(StockDateEntityPO::getVolume).average();
                            OHLCResult2 ohlcResult2 = new OHLCResult2();
                            ohlcResult2.setName(name);
                            OHLCSub ohlcSub = new OHLCSub();
                            ohlcSub.setOpenAvg(openAvg.getAsDouble());
                            ohlcSub.setOpenMAx(openMax.getAsDouble());
                            ohlcSub.setOpenMin(openMin.getAsDouble());
                            ohlcSub.setVolumeAvg((int)volumeAvg.getAsDouble());
                            ohlcSub.setVolumeMax((int)volumeMax.getAsDouble());
                            ohlcSub.setVolumeMin((int)volumeMin.getAsDouble());
                            ohlcResult2.setOhlcSub(ohlcSub);
                            ohlcResult2.setMonth(key);
                            return ohlcResult2;
                        }).collect(Collectors.toList());
                break;
            case 2:
                result2 = result.stream().collect(Collectors.groupingBy(StockDateEntityPO::getYear))
                        .entrySet().stream().map(entry->{
                            Integer key = entry.getKey();
                            List<StockDateEntityPO>value = entry.getValue();
                            OptionalDouble openMax = value.stream().mapToDouble(StockDateEntityPO::getOpen).max();
                            OptionalDouble openMin = value.stream().mapToDouble(StockDateEntityPO::getOpen).min();
                            OptionalDouble openAvg = value.stream().mapToDouble(StockDateEntityPO::getOpen).average();
                            OptionalDouble volumeMax = value.stream().mapToDouble(StockDateEntityPO::getVolume).max();
                            OptionalDouble volumeMin = value.stream().mapToDouble(StockDateEntityPO::getVolume).min();
                            OptionalDouble volumeAvg = value.stream().mapToDouble(StockDateEntityPO::getVolume).average();
                            OHLCResult2 ohlcResult2 = new OHLCResult2();
                            ohlcResult2.setName(name);
                            OHLCSub ohlcSub = new OHLCSub();
                            ohlcSub.setOpenAvg(openAvg.getAsDouble());
                            ohlcSub.setOpenMAx(openMax.getAsDouble());
                            ohlcSub.setOpenMin(openMin.getAsDouble());
                            ohlcSub.setVolumeAvg((int)volumeAvg.getAsDouble());
                            ohlcSub.setVolumeMax((int)volumeMax.getAsDouble());
                            ohlcSub.setVolumeMin((int)volumeMin.getAsDouble());
                            ohlcResult2.setOhlcSub(ohlcSub);
                            ohlcResult2.setYear(key);
                            return ohlcResult2;
                        }).collect(Collectors.toList());
                break;


        }
        return result2;
    }
}
