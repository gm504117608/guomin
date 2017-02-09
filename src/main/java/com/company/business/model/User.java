package com.company.business.model;

import org.apache.commons.lang.builder.ToStringBuilder;

public class User {
    private String id;
    private String name;
    private String password;
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 重写toString方法 输出实体内容
     * @return
     */
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}