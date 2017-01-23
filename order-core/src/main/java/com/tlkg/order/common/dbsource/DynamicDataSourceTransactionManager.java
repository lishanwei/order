package com.tlkg.order.common.dbsource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * DynamicDataSourceTransactionManager
 * 
 * @author lishanwei
 * @date 2016-08-03
 */
public class DynamicDataSourceTransactionManager extends DataSourceTransactionManager
{
    
    private static final long serialVersionUID = 7160082287881717832L;
    
    public DynamicDataSourceTransactionManager()
    {
    }
    
    /**
     * 只读事务到从库 读写事务到主库
     */
    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition)
    {
        TransactionSynchronizationManager.setCurrentTransactionReadOnly(definition.isReadOnly());
        super.doBegin(transaction, definition);
    }
}
