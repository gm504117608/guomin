package com.company.learn.excel.excelImport2.validate;

import com.company.learn.excel.excelImport2.bean.ImportCellDesc;

import java.util.List;

/**
 * 单元格数据校验
 */
public abstract class AbstractValidator {
    /**
     * 如果校验没有错误，则返回这个串
     */
    public static final String OK = "OK";
    /**
     * 异常错误标识
     */
    public static final String SYSTEM_ERROR = "SYSTEM_ERROR";
    /**
     * 单元格的位置
     */
    private String cellRef;
    /**
     * 单元格的值
     */
    private String fieldValue;
    /**
     * 单元格
     */
    private ImportCellDesc cell;

    public final String getFieldValue() {
        return fieldValue;
    }

    public final void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getCellRef() {
        return cellRef;
    }

    public void setCellRef(String cellRef) {
        this.cellRef = cellRef;
    }

    public final ImportCellDesc getCell() {
        return cell;
    }

    public final void setCell(final ImportCellDesc cell) {
        this.cell = cell;
    }

    /**
     * 进行数据校验
     *
     * @return 如果数据合法，返回空；否则返回不合法的原因。
     */
    public abstract String processValidate();

}