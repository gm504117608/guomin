package com.company.learn.batchInsert.impl;

import com.company.learn.batchInsert.IBatchInsert;
import com.company.learn.batchInsert.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 默认使用jdbc自带的事物进行控制处理
 *
 */
public class BatchInsertDefault implements IBatchInsert {


    @Override
    public int batchInsert() {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement("INSERT INTO user(username, password, email, imgurl)VALUES(?, ?, ?, ?)");
            for (int i = 1; i <= JdbcUtil.COUNT; i++) {
                pstmt.clearParameters();
                pstmt.setString(1, "username" + i);
                pstmt.setString(2, "password" + i);
                pstmt.setString(3, "email@" + i + ".com");
                pstmt.setString(4, "http://imgurl/" + i);
                pstmt.execute();
            }
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, pstmt, null);
        }
        return 0;
    }
}
