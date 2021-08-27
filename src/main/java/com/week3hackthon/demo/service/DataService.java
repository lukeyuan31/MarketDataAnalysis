package com.week3hackthon.demo.service;

import com.week3hackthon.demo.entity.DataEntity;
import com.week3hackthon.demo.repository.DataRepository;
import com.week3hackthon.demo.entity.TopVolumeEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class DataService implements DataRepository {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public DataEntity findDataById(String name) {
        Query query = new Query(Criteria.where("Name").is(name));
        DataEntity dataEntity = mongoTemplate.findOne(query, DataEntity.class);
        return dataEntity;
    }
    //-------Get the price data for one company by name-----
    //-------Can also set up start date and end date
    @Override
    public List<DataEntity> getAllByCompanyName(String name,String startDate,String endDate){
        Query query = new Query(Criteria.where("Name").is(name).and("Date").gte(startDate).lt(endDate));
        query.with(Sort.by("Date").descending());
        List<DataEntity> list= mongoTemplate.find(query,DataEntity.class);
        return list;
    }
    //-------Get the top 10 trade volume companies-----------
    @Override
    public List<DataEntity> getTopTenVolume(){
        Aggregation aggregation=Aggregation.newAggregation(
                DataEntity.class,
                Aggregation.group("Name").sum("Volume").as("Volume")
                .last("Open").as("Open").last("Date").as("Date").last("High").as("High")
                .last("Low").as("Low").last("Close").as("Close")
                        .last("Name").as("Name"),
                Aggregation.project("Open","Date","High","Low","Close","Name","Volume"),
                Aggregation.sort(Sort.Direction.DESC,"Volume")
        );
        AggregationResults<DataEntity> aggregationResults=mongoTemplate.aggregate(aggregation,"stock_price",DataEntity.class);
        List<DataEntity> list=aggregationResults.getMappedResults();
        //System.out.println(list.get(1).toString());
        return list.subList(0,10);
    }

    @Override
    public List<TopVolumeEntity> getTenVolume(){
        Aggregation aggregation=Aggregation.newAggregation(
                DataEntity.class,
                Aggregation.group("Name").sum("Volume").as("totalVolume")
                .last("Name").as("name"),
                Aggregation.project("totalVolume","name"),
                Aggregation.sort(Sort.Direction.DESC,"totalVolume")
        );
        AggregationResults<TopVolumeEntity> aggregationResults=mongoTemplate.aggregate(aggregation,"stock_price", TopVolumeEntity.class);
        List<TopVolumeEntity> list=aggregationResults.getMappedResults();
        return list.subList(0,10);

    }

    @Override
    public DataEntity aggStocks(String name, String startDate, String endDate) {
        Query query = new Query(Criteria.where("Name").is(name).and("Date").gte(startDate).lt(endDate));
        query.with(Sort.by("High").descending()).limit(1);
        DataEntity dataEntity=mongoTemplate.findOne(query,DataEntity.class);
        return dataEntity;
    }

    @Override
    public List<DataEntity> queryStocks(String name,Double maxClose, Double minClose,String startDate,String endDate){
        Query query=new Query(Criteria.where("Name").is(name).and("Close").gte(minClose).lt(maxClose)
                .and("Date").gte(startDate).lt(endDate));
        List<DataEntity> dataEntities=mongoTemplate.find(query,DataEntity.class);
        return dataEntities;
    }


}
