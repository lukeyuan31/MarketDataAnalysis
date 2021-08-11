package com.week3hackthon.demo;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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

}
