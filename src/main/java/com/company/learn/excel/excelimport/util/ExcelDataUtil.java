package com.company.learn.excel.excelimport.util;


import com.company.learn.excel.excelimport.bean.ExcelData;
import com.company.learn.excel.excelimport.bean.ImportCellDesc;
import com.company.learn.excel.excelimport.bean.SimpleExcelData;

import java.util.*;

/**
 * 针对ExcelData的简单的处理类
 */
public class ExcelDataUtil {
    private ExcelDataUtil() {
    }

    /**
     * 将ExcelData转化为SimpleExcelData
     *
     * @param excelData excel导入数据封装对象
     * @return 返回SimpleExcelData数据对象
     */
    public static SimpleExcelData changeExcelDataToSimple(ExcelData excelData) {
        if (excelData == null) {
            return null;
        }
        SimpleExcelData simpleExcelData = new SimpleExcelData();

        Map<String, ImportCellDesc> onceData = excelData.getOnceData();
        if (onceData != null && onceData.size() > 0) {
            Map<String, String> simpleOnceData = new HashMap<String, String>();
            Set<String> keys = onceData.keySet();
            for (String key : keys) {
                if (StringUtil.isEmpty(key) || onceData.get(key) == null)
                    continue;
                simpleOnceData.put(key, onceData.get(key).getFieldValue());
            }
            simpleExcelData.setOnceData(simpleOnceData);
        }

        List<Map<String, ImportCellDesc>> repeatData = excelData.getRepeatData();
        if (repeatData != null && repeatData.size() > 0) {
            List<Map<String, String>> simpleRepeatData = new ArrayList<Map<String, String>>();
            for (Map<String, ImportCellDesc> map : repeatData) {
                if (map == null)
                    continue;
                Map<String, String> tmp = new HashMap<String, String>();
                Set<String> keys = map.keySet();
                for (String key : keys) {
                    if (StringUtil.isEmpty(key) || map.get(key) == null)
                        continue;
                    tmp.put(key, map.get(key).getFieldValue());
                }
                simpleRepeatData.add(tmp);
            }
            simpleExcelData.setRepeatData(simpleRepeatData);
        }
        return simpleExcelData;
    }
}