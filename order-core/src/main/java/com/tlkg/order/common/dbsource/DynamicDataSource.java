package com.tlkg.order.common.dbsource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 动态数据源
 * 
 * @author lishanwei
 * @date 2016-08-03
 */
public class DynamicDataSource extends AbstractRoutingDataSource
{
    
    public DynamicDataSource()
    {
    }
    
    protected Object determineCurrentLookupKey()
    {
        boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        if(readOnly)
            return "slave";
        else
            return "master";
    }
}
