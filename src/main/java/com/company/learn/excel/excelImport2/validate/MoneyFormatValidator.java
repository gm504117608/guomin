package com.company.learn.excel.excelImport2.validate;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 金额格式校验
 */
public class MoneyFormatValidator extends AbstractValidator {

    /**
     * 校验金额格式
     * @return 返回校验结果
     */
    public String processValidate() {

        if (StringUtils.isNotEmpty(getFieldValue()) && isNumber(getFieldValue())) {
            return OK;
        }

        return getCellRef() + "单元格数据 : " + getFieldValue() + ", 金额格式不合法!";
    }

    /**
     * 金额验证
     * @param str 金额字符串
     * @return 正确返回 true 错误返回 false
     */
    private static boolean isNumber(String str) {
        // 判断小数点后2位的数字的正则表达式
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
        Matcher match = pattern.matcher(str);
        return match.matches();
    }

}
