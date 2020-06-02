package com.example.demo.config.dbcluster;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author yangdong@0easy.com
 * @Date Created in 2020/5/21 11:10
 **/
public class MyRoutingDataSource extends AbstractRoutingDataSource {
    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }

}