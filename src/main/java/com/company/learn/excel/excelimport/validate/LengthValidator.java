package com.company.learn.excel.excelimport.validate;


import com.company.learn.excel.excelimport.util.StringUtil;

/**
 * 长度校验器
 */
public class LengthValidator extends AbstractValidator {

    /**
     * 单元格值最大长度
     */
//    private String maxLength;
    /**
     * 单元格值最小长度
     */
//    private String minLength;

    /**
     * 校验值的长度
     * @return 返回校验结果
     */
    public String processValidate() {

        if (StringUtil.isNotEmpty(getFieldValue()) && getFieldValue().length() >= Integer.parseInt(getMinLength())
                && getFieldValue().length() <= Integer.parseInt(getMaxLength())) {
            return OK;
        }

        return getCellRef() + "单元格数据 : " + getFieldValue() + ", 长度不合法, 必须在 " + getMinLength() + "~" + getMaxLength() + " 之间!";
    }

//    public String getMaxLength() {
//        return maxLength;
//    }
//
//    public void setMaxLength(String maxLength) {
//        this.maxLength = maxLength;
//    }
//
//    public String getMinLength() {
//        return minLength;
//    }
//
//    public void setMinLength(String minLength) {
//        this.minLength = minLength;
//    }
}