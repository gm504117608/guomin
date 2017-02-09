package com.company.learn.excel.excelimport.validate;


import com.company.learn.excel.excelimport.util.StringUtil;

/**
 * 校验数据不能为空
 */
public class NotNullValidator extends AbstractValidator {
    /**
     * 校验数据是否为空
     * @return 返回校验结果
     */
    public String processValidate() {
        if (StringUtil.isEmpty(getFieldValue())) {
            return getCellRef() + "单元格数据 : " + getFieldValue() + ", 不可以为空!";
        }
        return OK;
    }
}