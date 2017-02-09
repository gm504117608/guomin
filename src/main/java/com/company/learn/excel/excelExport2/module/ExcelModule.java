package com.company.learn.excel.excelExport2.module;

/**
 * 功能:输出Excel时,数据来源定义
 * <p/>
 * 说明:这里输出时需要的数据有2种模型:
 * data_module=1,表示所有的输出数据都从sheet_data_all中取得
 * data_module=2,表示不同的sheet,所使用的输出数据是不一样的,要从sheet_data_single中取得
 */
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelModule {
    /**
     * 一般情况下,所有的sheet使用相同的数据来源
     */
    private SheetModule sheet_data_all;
    /**
     * 特殊情况下,不同的sheet使用不同的数据来源
     */
    private List<SheetModule> sheet_data_single;
    /**
     * 使用的数据模型:1,使用sheet_data_all, 2:使用sheet_data_single
     */
    private int data_module = 1;
    /**
     * sheet的名称
     */
    private Map<Integer, String> sheetNameMap;

    /**
     * 所有sheet使用相同的数据
     */
    public ExcelModule(SheetModule allSheetData) {
        this.sheet_data_all = allSheetData;
        this.data_module = 1;
    }

    /**
     * 不同的sheet使用不同的数据源
     */
    public ExcelModule(List<SheetModule> sheet_data_single) {
        this.sheet_data_single = sheet_data_single;
        this.data_module = 2;
    }

    /**
     * 取sheet页所需要的离散数据
     * @param sheetIndex sheet序号，从0开始
     */
    public Map<String, String> getSheetOnceData(int sheetIndex) {
        if (data_module == 2) {
            // 如果不同的sheet使用不同的数据
            return getSheetDataSingleOnce(sheetIndex);
        } else {
            // 所有的sheet都使用相同的数据
            return (sheet_data_all == null) ? null : sheet_data_all.getOnceData();
        }
    }

    /**
     * 取sheet页所需要的列表数据
     * @param sheetIndex sheet序号，从0开始
     */
    public List<Map<String, String>> getMultiData(int sheetIndex) {
        if (data_module == 2) {
            return getSheetDataSingleMulti(sheetIndex);
        } else {
            return (sheet_data_all == null) ? null : sheet_data_all.getMultiData();
        }
    }

    /**
     * 取第sheetIndex个sheet的离散数据
     * @param sheetIndex sheet序号，从0开始
     * @return
     */
    private Map<String, String> getSheetDataSingleOnce(int sheetIndex) {
        if (sheet_data_single == null || sheet_data_single.size() <= 0 || sheet_data_single.size() <= sheetIndex) {
            return null;
        }
        return sheet_data_single.get(sheetIndex).getOnceData();
    }

    /**
     * 取第sheetIndex个sheet的列表数据
     * @param sheetIndex sheet序号，从0开始
     * @return
     */
    private List<Map<String, String>> getSheetDataSingleMulti(int sheetIndex) {
        if (sheet_data_single == null || sheet_data_single.size() <= 0 || sheet_data_single.size() <= sheetIndex) {
            return null;
        }
        return sheet_data_single.get(sheetIndex).getMultiData();
    }

    public Map<Integer, String> getSheetNameMap() {
        return sheetNameMap;
    }

    public void setSheetNameMap(Map<Integer, String> sheetNameMap) {
        this.sheetNameMap = sheetNameMap;
    }

    /**
     * 设置sheet名称
     *
     * @param sheetIndex sheet序号，从0开始
     * @param sheetName  sheet名称
     */
    public void setSheetName(Integer sheetIndex, String sheetName) {
        if (sheetIndex < 0 || StringUtils.isEmpty(sheetName)) {
            return;
        }
        if (sheetNameMap == null) {
            sheetNameMap = new HashMap<Integer, String>();
        }
        sheetNameMap.put(sheetIndex, sheetName);
    }

    /**
     * 根据sheetIndex,返回sheetName
     * @param sheetIndex sheet序号，从0开始
     * @return
     */
    public String getSheetName(Integer sheetIndex) {
        if (sheetIndex < 0 || sheetNameMap == null || sheetNameMap.size() <= 0) {
            return null;
        }
        return sheetNameMap.get(sheetIndex);
    }
}