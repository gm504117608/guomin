/**
 * 功能:定义一个Sheet中要输出的数据
 * 日期:2009-8-4
 * 作者:张学东
 */
package com.company.learn.excel.excelexport.module;


import com.company.learn.excel.excelexport.util.MapKeyUtil;

import java.util.List;
import java.util.Map;

public class SheetModule {
    // #colName#的数据
    private Map<String, String> onceData;
    // %colName%的数据
    private List<Map<String, String>> multData;

    public SheetModule(Map<String, String> onceData, List<Map<String, String>> multData) {
        this.onceData = MapKeyUtil.keyToUpper(onceData);
        this.multData = MapKeyUtil.keyToUpper(multData);
    }

    public Map<String, String> getOnceData() {
        return onceData;
    }

    public void setOnceData(Map<String, String> onceData) {
        this.onceData = onceData;
    }

    public List<Map<String, String>> getMultData() {
        return multData;
    }

    public void setMultData(List<Map<String, String>> multData) {
        this.multData = multData;
    }
}