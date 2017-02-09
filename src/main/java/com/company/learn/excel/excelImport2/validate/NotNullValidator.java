package com.company.learn.excel.excelImport2.validate;


import org.apache.commons.lang3.StringUtils;

/**
 * 校验数据不能为空
 */
public class NotNullValidator extends AbstractValidator {
    /**
     * 校验数据是否为空
     * @return 返回校验结果
     */
    public String processValidate() {
        if (StringUtils.isEmpty(getFieldValue())) {
            return getCellRef() + "单元格数据 : " + getFieldValue() + ", 不可以为空!";
        }
        return OK;
    }
}