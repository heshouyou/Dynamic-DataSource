package com.heshouyou.core;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Author heshouyou
 * @Description  动态数据源
 * @Date 2018-01-16 14:41.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
