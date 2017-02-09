package com.company.common.dao;

import java.util.List;

public interface IBaseDao {

    /**
     * 获取满足查询参数条件的数据总数
     * @param _mybitsId mapper文件中的namespace + 执行sql配置id
     * @param _obj 数据实体
     * @return 存在的数据总条数
     */
    Long count(final String _mybitsId, Object _obj);

    /**
     * 新增指定的数据库记录
     * @param _mybitsId mapper文件中的namespace + 执行sql配置id
     * @param _obj 数据实体
     * @return 执行成功的记录个数
     */
    int insert(final String _mybitsId, Object _obj);

    /**
     * 更新指定的数据库记录
     * @param _mybitsId mapper文件中的namespace + 执行sql配置id
     * @param _obj 数据实体
     * @return 执行成功的记录个数
     */
    int update(final String _mybitsId, Object _obj);

    /**
     * 删除指定的唯一标识符对应的数据库记录
     * @param _mybitsId mapper文件中的namespace + 执行sql配置id
     * @param _obj 数据实体
     */
    int delete(final String _mybitsId, Object _obj);

    /**
     * 根据指定条件查询结果
     * @param _mybitsId mapper文件中的namespace + 执行sql配置id
     * @param _obj 查询条件实体
     * @return 返回查询结果list
     */
    <T> List<T> query(final String _mybitsId, Object _obj);

    /**
     * 查询单条数据
     * @param _mybitsId  mapper文件中的namespace + 执行sql配置id
     * @param _obj 查询条件实体
     * @return 返回查询结果map（单条数据）
     */
    <T> T queryOne(final String _mybitsId, Object _obj);

}