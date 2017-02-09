package com.company.learn.excel.excelimport.validate;

import com.company.learn.excel.excelimport.bean.ImportCellDesc;

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
    /**
     * 一次导入的单元格集合
     */
    private List<ImportCellDesc> onceCellList;
    /**
     * 循环导入的单元格集合
     */
    private List<ImportCellDesc> repeatCellList;
    /**
     * Excel中所有的单元格集合
     */
    private List<ImportCellDesc> allCellList;

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

    public final List<ImportCellDesc> getOnceCellList() {
        return onceCellList;
    }

    public final void setOnceCellList(final List<ImportCellDesc> onceCellList) {
        this.onceCellList = onceCellList;
    }

    public final List<ImportCellDesc> getRepeatCellList() {
        return repeatCellList;
    }

    public final void setRepeatCellList(final List<ImportCellDesc> repeatCellList) {
        this.repeatCellList = repeatCellList;
    }

    public final List<ImportCellDesc> getAllCellList() {
        return allCellList;
    }

    public final void setAllCellList(final List<ImportCellDesc> allCellList) {
        this.allCellList = allCellList;
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



    /**
     * 单元格值最大长度
     */
    private String maxLength;
    /**
     * 单元格值最小长度
     */
    private String minLength;
    /**
     * 校验日期格式
     */
    private String dateFormat;


    public String getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }

    public String getMinLength() {
        return minLength;
    }

    public void setMinLength(String minLength) {
        this.minLength = minLength;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
}