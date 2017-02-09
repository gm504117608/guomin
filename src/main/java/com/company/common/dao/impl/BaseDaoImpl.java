package com.company.common.dao.impl;


import com.company.common.dao.IBaseDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;

@Repository("baseDao")
public class BaseDaoImpl extends SqlSessionDaoSupport implements IBaseDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDaoImpl.class);

    /**
     * 获取相关的数据库连接
     */
    public Connection getConnection() {
        return getSqlSession().getConnection();
    }

    /**
     * 获取满足查询参数条件的数据总数
     * @param _mybitsId mapper文件中的namespace + 执行sql配置id
     * @param _obj 数据实体
     * @return 存在的数据总条数
     */
    public Long count(final String _mybitsId, Object _obj){
       return (Long)this.getSqlSession().selectOne(_mybitsId, _obj);
    }

    /**
     * 新增指定的数据库记录
     * @param _mybitsId mapper文件中的namespace + 执行sql配置id
     * @param _obj 数据实体
     * @return 执行成功的记录个数
     */
    public int insert(final String _mybitsId, Object _obj){
        return this.getSqlSession().insert(_mybitsId, _obj);
    }

    /**
     * 更新指定的数据库记录
     * @param _mybitsId mapper文件中的namespace + 执行sql配置id
     * @param _obj 数据实体
     * @return 执行成功的记录个数
     */
    public int update(final String _mybitsId, Object _obj){
        return this.getSqlSession().update(_mybitsId, _obj);
    }

    /**
     * 删除指定的唯一标识符对应的数据库记录
     * @param _mybitsId mapper文件中的namespace + 执行sql配置id
     * @param _obj 数据实体
     */
    public int delete(final String _mybitsId, Object _obj){
        return this.getSqlSession().delete(_mybitsId, _obj);
    }

    /**
     * 根据指定条件查询结果
     * @param _mybitsId mapper文件中的namespace + 执行sql配置id
     * @param _obj 查询条件实体
     * @return 返回查询结果list
     */
    public <T> List<T> query(final String _mybitsId, Object _obj){
        List<T> results = null;
        results = this.getSqlSession().selectList(_mybitsId, _obj);
        return results;
    }

    /**
     * 查询单条数据
     * @param _mybitsId  mapper文件中的namespace + 执行sql配置id
     * @param _obj 查询条件实体
     * @return 返回查询结果map（单条数据）
     */
    public <T> T queryOne(final String _mybitsId, Object _obj){
        T result = null;
        result = this.getSqlSession().selectOne(_mybitsId, _obj);
        return result;
    }

}