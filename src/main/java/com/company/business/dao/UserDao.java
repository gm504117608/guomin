package com.company.business.dao;

import com.company.common.dao.IBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("userDao")
public class UserDao {

    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    @Resource
    private IBaseDao baseDao;

    /**
     * 获取满足查询参数条件的数据总数
     * @param _mybitsId mapper文件中的namespace + 执行sql配置id
     * @param _obj 数据实体
     * @return 存在的数据总条数
     */
    public Long count(final String _mybitsId, Object _obj){
        log.info("获取满足查询参数条件的数据总数");

        return baseDao.count(_mybitsId, _obj);
    }

}