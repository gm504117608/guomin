package com.company.learn.batchInsert.util;

import java.sql.*;

/**
 * 数据库操作工具类
 */
public final class JdbcUtil {

    public static final int COUNT = 2; // 总记录数
    public static final int BATCH_COUNT = 1; // 提交记录数
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/movie?useUnicode=true&characterEncoding=utf8";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "";

    private JdbcUtil(){

    }

    /**
     * 获取数据库连接
     * @return
     * @throws Exception
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        } catch (ClassNotFoundException e1) {
            System.out.println("加载数据库连接驱动找不到");
            e1.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库连接出现异常");
            e.printStackTrace();
        } catch (Exception e1) {
            System.out.println("未知人类出现");
            e1.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * 注意关闭的顺序
     * @param conn
     * @param pstmt
     * @param rs
     */
    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs){
        try{
            if(null != rs){
                rs.close();
            }
        }catch(SQLException e){
            System.out.println("关闭数据库连接出现异常");
            e.printStackTrace();
        }
        try{
            if(null != pstmt){
                pstmt.close();
            }
        }catch(SQLException e){
            System.out.println("关闭数据库连接出现异常");
            e.printStackTrace();
        }
        try{
            if(null != conn){
                conn.close();
            }
        }catch(SQLException e){
            System.out.println("关闭数据库连接出现异常");
            e.printStackTrace();
        }

    }
}
