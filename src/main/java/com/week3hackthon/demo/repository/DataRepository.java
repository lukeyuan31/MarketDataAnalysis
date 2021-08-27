package com.week3hackthon.demo.repository;

import com.week3hackthon.demo.entity.DataEntity;
import com.week3hackthon.demo.entity.TopVolumeEntity;

import java.util.List;

public interface DataRepository {
    DataEntity findDataById(String name);
 //   List<Entity> findListByState(String state);
    List<DataEntity> getAllByCompanyName(String name,String startDate,String endDate);
    List<DataEntity> getTopTenVolume();
    DataEntity aggStocks(String name,String startDate, String endDate);
    List<DataEntity> queryStocks(String name,Double maxClose, Double minClose, String startDate, String endDate);
    List<TopVolumeEntity> getTenVolume();
}
