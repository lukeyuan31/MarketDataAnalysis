package com.week3hackthon.demo;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public interface DataRepository {
    DataEntity findDataById(String name);
 //   List<Entity> findListByState(String state);
    List<DataEntity> getAllByCompanyName(String name);
    List<DataEntity> getTopTenVolume();
}
