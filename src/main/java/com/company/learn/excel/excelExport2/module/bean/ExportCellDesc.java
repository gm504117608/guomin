package com.company.learn.excel.excelExport2.module.bean;

import org.apache.poi.hssf.util.CellReference;

/**
 * 单元格的描述信息
 * 可以精确定位到某个单元格。
 */
public class ExportCellDesc {
    /**
     * 引用的单元格；如：A3
     */
    private String cellRef;
    /**
     * 单元格的对应数据库的字段名称；
     * 如：fieldName = "username"
     */
    private String fieldName;

    public ExportCellDesc() {
    }

    public ExportCellDesc(ExportCellDesc o) {
        if (o != null) {
            this.cellRef = o.getCellRef();
            this.fieldName = o.getFieldName();
        }
    }

    public ExportCellDesc(String cellRef, String fieldName) {
        this.cellRef = cellRef;
        this.fieldName = fieldName;
    }

    /**
     * 返回单元格的行标（从1开始）
     * cellRef 如：B5 返回行坐标为 5
     * @return 5
     */
    public int getCellRow() {
        CellReference ref = new CellReference(cellRef);
        return ref.getRow() + 1;
    }

    /**
     * 返回单元格的列标（从1开始）
     * cellRef 如：B5 返回列坐标为 2
     * @return 2
     */
    public int getCellCol() {
        CellReference ref = new CellReference(cellRef);
        return ref.getCol() + 1;
    }

    /**
     * 输出ExportCellDesc对象的信息
     * @return 返回ExportCellDesc对象内容信息
     */
    public String toString() {
        return "(" + getCellRef() + " : " + getFieldName() + ")";
    }

    public String getCellRef() {
        return cellRef;
    }

    public void setCellRef(String cellRef) {
        this.cellRef = cellRef;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

}