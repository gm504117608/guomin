package com.company.learn.dataSourcePool.impl;

import com.company.learn.dataSourcePool.bean.DataBaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 初始化，模拟加载所有的配置文件
 * 数据库连接配置
 *
 */
public class DataBaseInfo {

    public static List<DataBaseBean> beans = null;

    static {
        beans = new ArrayList<DataBaseBean>();
        // 这里数据 可以从xml 等配置文件进行获取

        // 为了测试，这里我直接写死
        DataBaseBean bean = new DataBaseBean();
        bean.setDriverName("com.mysql.jdbc.Driver");
        bean.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8");
        bean.setUserName("root");
        bean.setPassword("");

        bean.setMinConnections(5);
        bean.setMaxConnections(100);

        bean.setPoolName("testPool1");
        beans.add(bean);

        // 为了测试，这里我直接写死
        DataBaseBean bean1 = new DataBaseBean();
        bean1.setDriverName("com.mysql.jdbc.Driver");
        bean1.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8");
        bean1.setUserName("root");
        bean1.setPassword("");

        bean1.setMinConnections(10);
        bean1.setMaxConnections(80);

        bean1.setPoolName("testPool2");
        beans.add(bean1);
    }
}
