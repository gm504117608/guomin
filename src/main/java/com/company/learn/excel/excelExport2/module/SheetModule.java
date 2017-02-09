package com.company.learn.excel.excelExport2.module;

/**
 * 功能:定义一个Sheet中要输出的数据
 */

import java.util.List;
import java.util.Map;

public class SheetModule {
    /**
     * 离散的数据
     */
    private Map<String, String> onceData;
    /**
     * 列表的数据
     */
    private List<Map<String, String>> multiData;

    public SheetModule(Map<String, String> onceData, List<Map<String, String>> multiData) {
        this.onceData = onceData;
        this.multiData = multiData;
    }

    public Map<String, String> getOnceData() {
        return onceData;
    }

    public void setOnceData(Map<String, String> onceData) {
        this.onceData = onceData;
    }

    public List<Map<String, String>> getMultiData() {
        return multiData;
    }

    public void setMultData(List<Map<String, String>> multiData) {
        this.multiData = multiData;
    }
}