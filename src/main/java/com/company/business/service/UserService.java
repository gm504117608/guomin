package com.company.business.service;


import com.company.business.model.User;

public interface UserService {

    /**
     * 通过名称获取用户信息
     * @param name
     * @return
     */
    User getUserByName(String name);

    /**
     * 通过id获取用户信息
     * @param id
     * @return
     */
    User getUserById(String id);

    /**
     * 增加用户信息
     * @param user
     * @return
     */
    boolean addUser(User user);
}