package com.company.learn.dataSourcePool;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionPool {

    /**
     * 获得连接
     */
    public Connection getConnection();

    /**
     * 获得当前连接
     */
    public Connection getCurrentConnection();

    /**
     * 回收连接
     * @param conn
     * @throws SQLException
     */
    public void releaseConn(Connection conn) throws SQLException;

    /**
     * 销毁清空
     */
    public void destroy();

    /**
     * 连接池是活动状态
     * @return
     */
    public boolean isActive();

    /**
     * 定时器，检查连接池
     */
    public void checkPool();

    /**
     * 连接池中是否存在该连接
     * @param conn
     * @return 存在 ture 不存在 false
     */
    public boolean isExit(Connection conn);
}