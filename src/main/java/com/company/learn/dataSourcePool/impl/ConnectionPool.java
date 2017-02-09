package com.company.learn.dataSourcePool.impl;

import com.company.learn.dataSourcePool.IConnectionPool;
import com.company.learn.dataSourcePool.bean.DataBaseBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 连接池的基本操作实现
 * 单个连接池的操作封装
 */
public class ConnectionPool implements IConnectionPool {

    // 连接池配置属性
    private DataBaseBean dataBaseBean;
    // 连接池活动状态
    private AtomicBoolean isActive = new AtomicBoolean(false);
    // 记录创建的总的连接数
    private AtomicInteger contActive = new AtomicInteger(0);
    // 空闲连接
    private CopyOnWriteArrayList<Connection> freeConnection = null;
    // 活动连接
    private CopyOnWriteArrayList<Connection> activeConnection = null;
    // 将线程和连接绑定，保证事务能统一执行
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    public ConnectionPool(DataBaseBean dataBaseBean) {
        super();
        this.dataBaseBean = dataBaseBean;
        init();
        checkPool();
    }

    /**
     * 初始化
     */
    public void init() {
        freeConnection = new CopyOnWriteArrayList<Connection>();
        activeConnection = new CopyOnWriteArrayList<Connection>();
        try {
//            Class.forName(dataBaseBean.getDriverName());
            for (int i = 0; i < dataBaseBean.getInitConnections(); i++) {
                Connection conn;
                conn = newConnection();
                // 初始化最小连接数
                if (conn != null) {
                    freeConnection.add(conn);
//                    contActive.incrementAndGet();
                }
            }
            isActive.set(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得当前连接
     * @return
     */
    public Connection getCurrentConnection() {
        // 默认线程里面取
        Connection conn = threadLocal.get();
        if (!isValid(conn)) {
            conn = getConnection();
        }
        return conn;
    }

    /**
     * 获得连接
     * @return
     */
    public synchronized Connection getConnection() {
        Connection conn = null;
        try {
            // 判断是否超过最大连接数限制
            if (contActive.get() < this.dataBaseBean.getMaxActiveConnections()) {
                if (freeConnection.size() > 0) {
                    conn = freeConnection.get(0);
                    if (conn != null) {
                        threadLocal.set(conn);
                    }
                    freeConnection.remove(0);
                } else {
                    conn = newConnection();
                }
            } else {
                // 继续获得连接,直到从新获得连接
                wait(this.dataBaseBean.getConnTimeOut());
                conn = getConnection();
            }
            if (isValid(conn)) {
                activeConnection.add(conn);
                contActive.incrementAndGet();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 获得新连接
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private synchronized Connection newConnection()
            throws ClassNotFoundException, SQLException {
        Connection conn = null;
        if (dataBaseBean != null) {
            Class.forName(dataBaseBean.getDriverName());
            conn = DriverManager.getConnection(dataBaseBean.getUrl(),
                    dataBaseBean.getUserName(), dataBaseBean.getPassword());
        }
        return conn;
    }

    /**
     * 释放连接
     * @param conn
     * @throws SQLException
     */
    public synchronized void releaseConn(Connection conn) throws SQLException {
        if (isValid(conn) && !(freeConnection.size() > dataBaseBean.getMaxConnections())) {
            freeConnection.add(conn);
            activeConnection.remove(conn);
            contActive.decrementAndGet();
            threadLocal.remove();
            // 唤醒所有正待等待的线程，去抢连接
            notifyAll();
        }
    }

    /**
     * 判断连接是否可用
     * @param conn
     * @return
     */
    private boolean isValid(Connection conn) {
        try {
            if (conn == null || conn.isClosed()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 销毁连接池
     */
    public void destroy() {
        destroy(freeConnection);
        destroy(activeConnection);
        isActive.set(false);
        contActive.set(0);
    }

    /**
     * 销毁连接
     * @param connections
     */
    private void destroy(CopyOnWriteArrayList<Connection> connections){
        for(Connection conn : connections){
            try {
                if (isValid(conn)) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 连接池状态
     * @return
     */
    @Override
    public boolean isActive() {
        return isActive.get();
    }

    /**
     * 定时检查连接池情况
     */
    @Override
    public void checkPool() {
        if (dataBaseBean.isCheckPool()) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    // 1.对线程里面的连接状态
                    // 2.连接池最小 最大连接数
                    // 3.其他状态进行检查，因为这里还需要写几个线程管理的类，暂时就不添加了
                    System.out.println("空线池连接数：" + freeConnection.size());
                    System.out.println("活动连接数：：" + activeConnection.size());
                    System.out.println("总的连接数：" + contActive.get());
                }
            }, dataBaseBean.getLazyCheck(), dataBaseBean.getPeriodCheck());
        }
    }

    /**
     * 连接池中是否存在该连接
     * @param conn
     * @return 存在 ture 不存在 false
     */
    public boolean isExit(Connection conn){
        return freeConnection.contains(conn) || activeConnection.contains(conn);
    }
}