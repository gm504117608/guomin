package com.company.learn.excel.excelImport2.validate;

import org.apache.commons.lang3.StringUtils;

/**
 * 校验导入的值是否在指定的范围之内；
 * 比如：导入值 55；
 * 给定范围 "33|45|55" ，使用|拆分；
 * 校验 55 是否在 "33|45|55" 里面；
 */
public class ContainValueValidator extends AbstractValidator {

    /**
     * 给定值的串
     * 比如 "33|45|55"
     */
    private String containStr;

    /**
     * 校验日期格式
     *
     * @return 返回校验结果
     */
    public String processValidate() {

        if (StringUtils.isNotEmpty(getFieldValue()) && isContain(getFieldValue())) {
            return OK;
        }
        return getCellRef() + "单元格数据 : " + getFieldValue() + ", 给定值不合法, 必须为 " + containStr + " 中的一个!";
    }

    /**
     * 判断给定的值是否在指定的串里面，不区分大小写
     *
     * @param value 传入值
     * @return 存在返回 true 否则 返回 false
     */
    private boolean isContain(String value){
        String[] array = containStr.split("\\|");
        for(String str : array){
            if(value.toUpperCase().equals(str.toUpperCase())){
                return true;
            }
        }
       return false;
    }

    public String getContainStr() {
        return containStr;
    }

    public void setContainStr(String containStr) {
        this.containStr = containStr;
    }
}
