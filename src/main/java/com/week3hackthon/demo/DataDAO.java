package com.week3hackthon.demo;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public interface DataDAO {
    DataEntity findDataById(String state);
 //   List<Entity> findListByState(String state);
}
