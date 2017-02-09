package com.company.business.service.impl;


import com.company.business.dao.UserDao;
import com.company.business.model.User;
import com.company.business.service.UserService;
import com.company.common.dao.IBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private IBaseDao baseDao;

    @Resource
    private UserDao userDao;

    private static String nameSpace = "N_USER.";

    /**
     * 通过名称获取用户信息
     * @param name
     * @return
     */
    public User getUserByName(String name) {
        LOGGER.info("通过名称获取用户信息name=" + name);

        User user = new User();
        user.setName(name);
        Long l = userDao.count(nameSpace + "count", user);
        LOGGER.info("获取到数据条数：" + l.intValue());
        return baseDao.queryOne(nameSpace + "get", user);
    }

    /**
     * 通过id获取用户信息
     * @param id
     * @return
     */
    public User getUserById(String id) {
        LOGGER.info("通过id获取用户信息id=" + id);

        User user = new User();
        user.setId(id);
        return baseDao.queryOne(nameSpace + "get", user);
    }

    /**
     * 增加用户信息
     * @param user
     * @return
     */
    public boolean addUser(User user) {
        LOGGER.info("增加用户信息=" + user.toString());

        user.setId("1121" + new Date().getTime());
        boolean flag = baseDao.insert(nameSpace + "add", user) > 0 ? true : false;
        return flag;
    }


}