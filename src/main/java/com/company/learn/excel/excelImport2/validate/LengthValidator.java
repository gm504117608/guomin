package com.company.learn.excel.excelImport2.validate;


import org.apache.commons.lang3.StringUtils;

/**
 * 长度校验器
 */
public class LengthValidator extends AbstractValidator {

    /**
     * 单元格值最大长度
     */
    private int maxLength;
    /**
     * 单元格值最小长度
     */
    private int minLength;

    /**
     * 校验值的长度
     * @return 返回校验结果
     */
    public String processValidate() {

        if (StringUtils.isNotEmpty(getFieldValue()) && getFieldValue().length() >= minLength
                && getFieldValue().length() <= maxLength) {
            return OK;
        }

        return getCellRef() + "单元格数据 : " + getFieldValue() + ", 长度不合法, 必须在 " + minLength + "~" + maxLength + " 之间!";
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }
}