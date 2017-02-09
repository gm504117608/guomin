package com.company.learn.excel.excelimport.bean.validate;

/**
 * 校验器定义封装
 */
public class Validator {
    /**
     * 校验类的名称
     */
    private String name;
    /**
     * 校验类的路径
     */
    private String value;

    public Validator(){}

    public Validator(String name,  String value){
        this.name = name;
        this.value = value;
    }

    public Validator(Validator v){
        this.name = v.getName();
        this.value = v.getValue();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Validator{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
