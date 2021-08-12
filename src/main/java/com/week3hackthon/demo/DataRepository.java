package com.week3hackthon.demo;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public interface DataRepository {
    DataEntity findDataById(String name);
 //   List<Entity> findListByState(String state);
    List<DataEntity> getAllByCompanyName(String name,String startDate,String endDate);
    List<DataEntity> getTopTenVolume();
    DataEntity aggStocks(String name,String startDate, String endDate);
    List<DataEntity> queryStocks(Double maxClose, Double minClose, String startDate, String endDate);
}
