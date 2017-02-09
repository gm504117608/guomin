package com.company.learn.excel.excelExport2.module.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Excel导出描述文件的结构
 */
public class ExcelExportStruct {
    /**
     * 离散数据导出的单元格描述信息
     */
    private List<ExportCellDesc> onceExportCells = null;
    /**
     * 列表数据导出的单元格描述信息
     */
    private List<ExportCellDesc> repeatExportCells = null;
    /**
     * 导出数据开始的行号（所有sheet页是一样的 默认第二行 从0开始）
     */
    private int startLine = 1;
    /**
     * 每个sheet页中导出数据量(默认4000行)
     */
    private int exportNumber = 4000;

    public ExcelExportStruct() {
        this.onceExportCells = new ArrayList<ExportCellDesc>();
        this.repeatExportCells = new ArrayList<ExportCellDesc>();
    }

    public ExcelExportStruct(List<ExportCellDesc> onceExportCells, List<ExportCellDesc> repeatExportCells,
                             int exportNumber, int startLine) {
        this.onceExportCells = onceExportCells;
        this.repeatExportCells = repeatExportCells;
        this.exportNumber = exportNumber;
        this.startLine = startLine;
    }

    public List<ExportCellDesc> getOnceExportCells() {
        return onceExportCells;
    }

    public void setOnceExportCells(List<ExportCellDesc> onceExportCells) {
        this.onceExportCells = onceExportCells;
    }

    public List<ExportCellDesc> getRepeatExportCells() {
        return repeatExportCells;
    }

    public void setRepeatExportCells(List<ExportCellDesc> repeatExportCells) {
        this.repeatExportCells = repeatExportCells;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getExportNumber() {
        return exportNumber;
    }

    public void setExportNumber(int exportNumber) {
        this.exportNumber = exportNumber;
    }

    /**
     * 列表数据的获取
     * 离散数据的获取
     * 通过单元格的位置CellRef（比如：A3）获取单元格对应的数据库字段名称
     * @return
     */
    public static String getFieldNameByCellRef(List<ExportCellDesc> list, String cellRef){
        for(ExportCellDesc ecd : list){
            if(ecd.getCellRef().equals(cellRef)){
                return ecd.getFieldName();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ExcelExportStruct{" +
                "onceExportCells=" + onceExportCells +
                ", repeatExportCells=" + repeatExportCells +
                ", startLine=" + startLine +
                ", exportNumber=" + exportNumber +
                '}';
    }
}