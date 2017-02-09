package com.company.learn.excel.excelImport2.bean;

import com.company.learn.excel.excelImport2.util.CellRefComparator;
import com.company.learn.excel.excelImport2.validate.AbstractValidator;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Excel导入的数据信息封装
 */
public class ExcelData {
    /**
     * 离散数据（key是字段名）
     */
    private Map<String, ImportCellDesc> onceData;
    /**
     * 列表数据（key是字段名），一个map是单元格中一行数据
     */
    private List<Map<String, ImportCellDesc>> repeatData;
    /**
     * 数据校验时的异常信息(key是单元格的定位地址；如：A3)
     */
    private Map<String, Set<String>> errorMsgList;

    public ExcelData() {
    }

    public Map<String, ImportCellDesc> getOnceData() {
        return onceData;
    }

    public void setOnceData(Map<String, ImportCellDesc> onceData) {
        this.onceData = onceData;
    }

    public List<Map<String, ImportCellDesc>> getRepeatData() {
        return repeatData;
    }

    public void setRepeatData(List<Map<String, ImportCellDesc>> repeatData) {
        this.repeatData = repeatData;
    }

    public Map<String, Set<String>> getErrorMsgList() {
        return errorMsgList;
    }

    /**
     * 返回错误信息
     *
     * @return 获取错误信息
     */
    public String getErrorMsg() {
        if (null == errorMsgList || errorMsgList.size() <= 0) {
            return null;
        }
        StringBuilder buf = new StringBuilder(500);
        Set<String> keys = errorMsgList.keySet();
        for (String key : keys) {
            Set<String> errorList = errorMsgList.get(key);
            if (null == errorList || errorList.size() <= 0) {
                continue;
            }
            for (String msg : errorList) {
                if (StringUtils.isNotEmpty(msg) && !AbstractValidator.OK.equals(msg)) {
                    buf.append(msg).append("\n");
                }
            }
        }
        return buf.toString();
    }

    public void setErrorMsgList(Map<String, Set<String>> errorMsgList) {
        this.errorMsgList = errorMsgList;
    }

    /**
     * 增加错误信息
     *
     * @param cellRef  单元格位置 如： A4
     * @param errorMsg 错误信息
     */
    public void addErrorMsg(String cellRef, String errorMsg) {
        if (StringUtils.isEmpty(cellRef) || StringUtils.isEmpty(errorMsg) || AbstractValidator.OK.equals(errorMsg)) {
            return;
        }
        if (null == this.errorMsgList) {
            this.errorMsgList = new TreeMap<String, Set<String>>(new CellRefComparator());
        }
        Set<String> errorList = errorMsgList.get(cellRef);
        if (null == errorList) {
            errorList = new HashSet<String>();
            errorMsgList.put(cellRef, errorList);
        }
        errorList.add(errorMsg);
    }

    /**
     * 返回excel导入所有数据信息
     *
     * @return excel导入所有数据信息
     */
    public String toString() {
        StringBuilder buf = new StringBuilder(1000);
        if (null != onceData && onceData.size() > 0) {
            buf.append("-----------------------------离散数据-----------------------------\n");
            for (String key : onceData.keySet()) {
                buf.append((onceData.get(key))).append("\n");
            }
        }
        if (null != repeatData && repeatData.size() > 0) {
            buf.append("*****************************列表数据*****************************\n");
            for (Map<String, ImportCellDesc> map : repeatData) {
                if (null != map && map.size() > 0) {
                    buf.append("^^^^^^^^^^^^^^^^^^^^^^^^^一行数据^^^^^^^^^^^^^^^^^^^^^^^^^\n");
                    for (String key : map.keySet()) {
                        buf.append((map.get(key))).append("\n");
                    }
                }
            }
        }
        if (null != this.errorMsgList && this.errorMsgList.size() > 0) {
            buf.append("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^异常信息^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
            buf.append(getErrorMsg());
        }
        return buf.toString();
    }
}