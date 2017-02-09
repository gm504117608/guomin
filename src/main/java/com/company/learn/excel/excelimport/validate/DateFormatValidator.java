package com.company.learn.excel.excelimport.validate;

import com.company.learn.excel.excelimport.util.StringUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式校验
 */
public class DateFormatValidator extends AbstractValidator {

    /**
     * 校验日期格式
     * @return 返回校验结果
     */
    public String processValidate() {

        if (StringUtil.isNotEmpty(getFieldValue()) && isValidDate(getFieldValue(), getDateFormat())) {
            return OK;
        }

        return getCellRef() + "单元格数据 : " + getFieldValue() + ", 日期不合法, 必须为 " + getDateFormat() + " !";
    }

    /**
     * 判断时间格式 通过传入的format
     * @param str 日期字符串
     * @param format 日期格式
     * @return
     */
    public boolean isValidDate(String str, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        try{
            Date date = (Date)formatter.parse(str);
            return str.equals(formatter.format(date));
        }catch(Exception e){
            return false;
        }
    }
}
