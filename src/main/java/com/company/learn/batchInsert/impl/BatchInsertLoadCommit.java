package com.company.learn.batchInsert.impl;

import com.company.learn.batchInsert.IBatchInsert;
import com.company.learn.batchInsert.util.JdbcUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 加载数据文件之后再操作数据
 */
public class BatchInsertLoadCommit implements IBatchInsert {

    @Override
    public int batchInsert() {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("LOAD DATA LOCAL INFILE 'sql.csv' IGNORE INTO TABLE user (username, password, email, imgurl)");
            InputStream is = getTestDataInputStream();
            ((com.mysql.jdbc.Statement) pstmt).setLocalInfileInputStream(is);
            pstmt.execute();
            conn.commit();
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


    public static InputStream getTestDataInputStream() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= JdbcUtil.COUNT; i++) {
            builder.append("username" + i);
            builder.append("\t");
            builder.append("password" + i);
            builder.append("\t");
            builder.append("email@" + i + ".com");
            builder.append("\t");
            builder.append("http://imgurl/" + i);
            builder.append("\n");
        }
        byte[] bytes = builder.toString().getBytes();
        InputStream is = new ByteArrayInputStream(bytes);
        return is;
    }
}
