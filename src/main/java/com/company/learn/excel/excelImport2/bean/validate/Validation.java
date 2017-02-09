package com.company.learn.excel.excelImport2.bean.validate;

/**
 * 单元格每一种校验类型的封装
 */
public class Validation {
    /**
     * 校验类的路径
     */
    private String name;
    /**
     * 单元格值最小值
     */
    private int minLength;
    /**
     * 单元格值最大值
     */
    private int maxLength;
    /**
     * 校验日期格式
     */
    private String dateFormat;
    /**
     * 给定值的串
     * 比如 "33|45|55"
     */
    private String containStr;

    public Validation(String name, int minLength, int maxLength,
                      String dateFormat, String containStr){
        this.name = name;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.dateFormat = dateFormat;
        this.containStr = containStr;
    }

    public Validation(String name){
        this.name = name;
    }

    public Validation(Validation v){
        if(null != v){
            this.name = v.getName();
            this.minLength = v.getMinLength();
            this.maxLength = v.getMaxLength();
            this.dateFormat = v.getDateFormat();
            this.containStr = v.getContainStr();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getContainStr() {
        return containStr;
    }

    public void setContainStr(String containStr) {
        this.containStr = containStr;
    }


    @Override
    public String toString() {
        return "Validation{" +
                "name='" + name + "';" +
                ", minLength=" + minLength + ";" +
                ", maxLength=" + maxLength + ";" +
                ", dateFormat='" + dateFormat + "';" +
                ", containStr='" + containStr + "';" +
                '}';
    }
}
