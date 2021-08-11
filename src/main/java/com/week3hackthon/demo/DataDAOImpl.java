package com.week3hackthon.demo;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Queue;

@Component
public class DataDAOImpl implements DataDAO {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public DataEntity findDataById(String state) {
        Query query = new Query(Criteria.where("State").is(state));
        DataEntity dataEntity = mongoTemplate.findOne(query, DataEntity.class);
        return dataEntity;
    }

}
