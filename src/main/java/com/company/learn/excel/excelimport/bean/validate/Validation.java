package com.company.learn.excel.excelimport.bean.validate;

/**
 * 单元格每一个校验类型的封装
 */
public class Validation {
    /**
     * 校验类的路径
     */
    private String name;
    /**
     * 单元格值最小值
     */
    private String minLength;
    /**
     * 单元格值最大值
     */
    private String maxLength;
    /**
     * 校验日期格式
     */
    private String dateFormat;

    public Validation(String name, String minLength, String maxLength, String dateFormat){
        this.name = name;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.dateFormat = dateFormat;
    }

    public Validation(Validation v){
        if(null != v){
            this.name = v.getName();
            this.minLength = v.getMinLength();
            this.maxLength = v.getMaxLength();
            this.dateFormat = v.getDateFormat();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinLength() {
        return minLength;
    }

    public void setMinLength(String minLength) {
        this.minLength = minLength;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public String toString() {
        return "Validation{" +
                "name='" + name + '\'' +
                ", minLength='" + minLength + '\'' +
                ", maxLength='" + maxLength + '\'' +
                ", dateFormat='" + dateFormat + '\'' +
                '}';
    }
}
