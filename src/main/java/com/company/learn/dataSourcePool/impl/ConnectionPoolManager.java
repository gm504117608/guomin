package com.company.learn.dataSourcePool.impl;

import com.company.learn.dataSourcePool.IConnectionPool;
import com.company.learn.dataSourcePool.bean.DataBaseBean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 连接管理类
 * 所有的连接池都在这个类中进行管理
 * 单例模式产生一个连接池管理对象
 */
public class ConnectionPoolManager {

    // 连接池存放
    public ConcurrentHashMap<String, IConnectionPool> pools = new ConcurrentHashMap<String, IConnectionPool>();

    /**
     * 初始化
     */
    private ConnectionPoolManager() {
        init();
    }

    /**
     * 单例实现
     *
     * @return
     */
    public static ConnectionPoolManager getInstance() {
        return Singleton.instance;
    }

    /**
     * 内部类实现单例模式
     */
    private static class Singleton {
        private static ConnectionPoolManager instance = new ConnectionPoolManager();
    }


    /**
     * 初始化所有的连接池
     */
    public void init() {
        for (int i = 0, size = DataBaseInfo.beans.size(); i < size; i++) {
            DataBaseBean bean = DataBaseInfo.beans.get(i);
            IConnectionPool pool = new ConnectionPool(bean);
            if (pool != null) {
                pools.put(bean.getPoolName(), pool);
                System.out.println("Info:Init connection successed ->" + bean.getPoolName());
            }
        }
    }

    /**
     * 获得连接,根据连接池名字 获得连接
     *
     * @param poolName
     * @return
     */
    public Connection getConnection(String poolName) {
        Connection conn = null;
        if (pools.size() > 0 && pools.containsKey(poolName)) {
            conn = getPool(poolName).getConnection();
        } else {
            System.out.println("Error:Can't find this connecion pool ->" + poolName);
        }
        return conn;
    }

    /**
     * 获得当前连接,根据连接池名字 获得连接
     *
     * @param poolName
     * @return
     */
    public Connection getCurrentConnection(String poolName){
        Connection conn = null;
        if (pools.size() > 0 && pools.containsKey(poolName)) {
            conn = getPool(poolName).getCurrentConnection();
        } else {
            System.out.println("Error:Can't find this connecion pool ->" + poolName);
        }
        return conn;
    }

    /**
     * 关闭，回收连接
     *
     * @param poolName
     * @param conn
     */
    public void close(String poolName, Connection conn) {
        IConnectionPool pool = getPool(poolName);
        if (pool != null) {
            if (!pool.isExit(conn)) {
                System.out.println("连接不存在于该连接池中，所以不能关闭、回收。");
                return;
            }
            try {
                pool.releaseConn(conn);
                System.out.println("连接关闭，回收连接：" + poolName);
            } catch (SQLException e) {
                System.out.println("连接池销毁出现异常：" + e.getMessage());
                e.printStackTrace();
            }
        }else{
            System.out.println("连接池中不存在该连接：" + poolName);
        }
    }

    /**
     * 清空连接池
     *
     * @param poolName
     */
    public void destroy(String poolName) {
        IConnectionPool pool = getPool(poolName);
        if (pool != null) {
            pool.destroy();
            System.out.println("清空连接池：" + poolName);
        }else{
            System.out.println("连接池中不存在该连接：" + poolName);
        }
    }

    /**
     * 获得连接池
     *
     * @param poolName
     * @return
     */
    public IConnectionPool getPool(String poolName) {
        IConnectionPool pool = null;
        if (pools.size() > 0) {
            pool = pools.get(poolName);
        }
        return pool;
    }
}