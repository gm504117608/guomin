package com.company.learn.dataSourcePool;

import com.company.learn.dataSourcePool.impl.ConnectionPoolManager;

import java.sql.Connection;

/**
 * 模拟线程启动，去获得连接
 *
 */
public class ThreadConnection implements Runnable {

    private ConnectionPoolManager connectionPoolManager;

    private String poolName = null;

    public ThreadConnection(String poolName){
        this.poolName = poolName;
    }

    @Override
    public void run() {
        connectionPoolManager = ConnectionPoolManager.getInstance();
    }

    public Connection getConnection() {
        IConnectionPool pool = connectionPoolManager.getPool(poolName);
        Connection conn = null;
        if (pool != null && pool.isActive()) {
            conn = pool.getConnection();
        }
        return conn;
    }

    public Connection getCurrentConnection() {
        Connection conn = connectionPoolManager.getCurrentConnection(poolName);
        return conn;
    }
}